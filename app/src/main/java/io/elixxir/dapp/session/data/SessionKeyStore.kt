package io.elixxir.dapp.session.data

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyInfo
import android.security.keystore.KeyProperties
import bindings.Bindings
import io.elixxir.dapp.model.CommonProperties
import io.elixxir.dapp.preferences.KeyStorePreferences
import io.elixxir.dapp.session.model.SecureHardwareException
import io.elixxir.dapp.session.model.SessionPassword
import io.elixxir.dapp.util.fromBase64toByteArray
import io.elixxir.dapp.util.toBase64String
import kotlinx.coroutines.withContext
import java.security.*
import java.security.spec.MGF1ParameterSpec
import java.security.spec.RSAKeyGenParameterSpec
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.OAEPParameterSpec
import javax.crypto.spec.PSource
import kotlin.system.measureTimeMillis

internal interface SessionKeyStore {
    suspend fun createSessionPassword(requireSecureHardware: Boolean): Result<Unit>
    suspend fun rsaDecryptPassword(): SessionPassword
}

internal class DappSessionKeystore private constructor(
    properties: CommonProperties,
    private val preferences: KeyStorePreferences,
) : SessionKeyStore, CommonProperties by properties {

    override suspend fun createSessionPassword(requireSecureHardware: Boolean): Result<Unit> =
        withContext(dispatcher) {
            if (requireSecureHardware && !isHardwareBackedKeyStore()) {
                return@withContext Result.failure(SecureHardwareException())
            }

            try {
                deletePreviousKeys()
                generateKeys()
                generatePassword()
                Result.success(Unit)
            } catch (e: Exception) {
                logError(e.message ?: "An error occured while creating session password.")
                Result.failure(e)
            }
    }

    private fun generatePassword() {
        val encryptionTime = measureTimeMillis {
            val bytesNumber: Long = PASSWORD_LENGTH
            log("Generating a password...")
            log("Generating a password with $bytesNumber bytes")

            var secret: ByteArray
            val generationTime = measureTimeMillis {
                do {
                    secret = Bindings.generateSecret(bytesNumber)
                    log("Password (Bytearray): $secret")
                    log("Password (String64): ${secret.toBase64String()}")

                    val isAllZeroes = byteArrayOf(bytesNumber.toByte()).contentEquals(secret)
                    log("IsAllZeroes: $isAllZeroes")
                } while (isAllZeroes)
            }
            log("Total generation time: $generationTime ms")
            rsaEncryptPwd(secret)
        }
        log("Total encryption time: $encryptionTime ms")
    }

    private fun deletePreviousKeys() {
        val keystore = getKeystore()
        if (keystore.containsAlias(KEY_ALIAS)) {
            log("Deleting key alias")
            keystore.deleteEntry(KEY_ALIAS)
        }
    }

    private fun checkGenerateKeys(): Boolean {
        return try {
            val areKeysGenerated = generateKeys()
            if (areKeysGenerated) {
                log("Keystore keys successfully generated")
                true
            } else {
                log("Error generating keystore keys")
                false
            }
        } catch (err: Exception) {
            log("An error occured while generating keys.")
            false
        }
    }

    private fun generateKeys(): Boolean {
        val keystore = getKeystore()
        if (!keystore.containsAlias(KEY_ALIAS)) {
            log("Keystore alias does not exist, credentials")
            val keyGenerator = getKeyPairGenerator()
            keyGenerator.genKeyPair()
        } else {
            log("Keystore alias already exist")
        }

        return true
    }

    private fun getKeyPairGenerator(): KeyPairGenerator {
        val keyGenerator = KeyPairGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore"
        )
        val keyGenParameterSpec = getKeygenParamSpec()
        keyGenerator.initialize(keyGenParameterSpec)
        return keyGenerator
    }

    private fun getPrivateKey(): PrivateKey? {
        val keyStore: KeyStore = getKeystore()
        return keyStore.getKey(KEY_ALIAS, null) as PrivateKey?
    }

    private fun getPublicKey(): PublicKey {
        return getKeystore().getCertificate(KEY_ALIAS).publicKey
    }

    private fun getKeystore(): KeyStore {
        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE)
        keyStore.load(null)
        return keyStore
    }

    private fun getKeygenParamSpec(): KeyGenParameterSpec {
        val keyGenSpec = KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KEY_PURPOSE
        ).setAlgorithmParameterSpec(RSAKeyGenParameterSpec(KEY_SIZE, RSAKeyGenParameterSpec.F4))
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_OAEP)
            .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
            .setDigests(KeyProperties.DIGEST_SHA1)
            .setRandomizedEncryptionRequired(true)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            keyGenSpec.setUserAuthenticationParameters(
                1000,
                KeyProperties.AUTH_BIOMETRIC_STRONG or KeyProperties.AUTH_DEVICE_CREDENTIAL
            )
        } else {
            keyGenSpec.setUserAuthenticationValidityDurationSeconds(1000)
        }

        return keyGenSpec.build()
    }

    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    private fun rsaEncryptPwd(pwd: ByteArray): ByteArray {
        log("Bytecount: ${pwd.size}")
        log("Before encryption: ${pwd.toBase64String()}")
        val secretKey = getPublicKey()

        val cipher = Cipher.getInstance(KEYSTORE_ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, cipherMode)
        val encryptedBytes = cipher.doFinal(pwd)
        log("Encrypted: ${encryptedBytes.toBase64String()}")
        preferences.userSecret = encryptedBytes.toBase64String()

        return encryptedBytes
    }

    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    override suspend fun rsaDecryptPassword(): SessionPassword = withContext(dispatcher) {
        val encryptedBytes = preferences.userSecret.fromBase64toByteArray()
        val key = getPrivateKey()
        val cipher1 = Cipher.getInstance(KEYSTORE_ALGORITHM)
        println("Initializing Decrypt")
        cipher1.init(Cipher.DECRYPT_MODE, key, cipherMode)
        val decryptedBytes = cipher1.doFinal(encryptedBytes)
        println("Decrypted: ${decryptedBytes.toBase64String()}")

        SessionPassword(decryptedBytes)
    }

    private fun isHardwareBackedKeyStore(): Boolean {
        return try {
            val privateKey = getPrivateKey()
            val keyFactory = KeyFactory.getInstance(privateKey?.algorithm, ANDROID_KEYSTORE)
            val keyInfo: KeyInfo = keyFactory.getKeySpec(privateKey, KeyInfo::class.java)
            val securityLevel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                (keyInfo.securityLevel == KeyProperties.SECURITY_LEVEL_TRUSTED_ENVIRONMENT ||
                        keyInfo.securityLevel == KeyProperties.SECURITY_LEVEL_STRONGBOX)
            } else {
                keyInfo.isInsideSecureHardware
            }
            return securityLevel
        } catch (e: Exception) {
            logError(e.message ?: "An error occured while checking hardware backed key store.")
            false
        }
    }

    companion object {
        private const val KEY_ALIAS = "dAppSession"
        private const val ANDROID_KEYSTORE = "AndroidKeyStore"
        private const val KEY_PURPOSE =
            KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        private const val KEYSTORE_ALGORITHM = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding"
        private const val KEY_SIZE = 2048
        private val cipherMode = OAEPParameterSpec(
            "SHA-1", "MGF1",
            MGF1ParameterSpec.SHA1, PSource.PSpecified.DEFAULT
        )
        private const val PASSWORD_LENGTH = 64L

        internal fun newInstance(properties: CommonProperties, preferences: KeyStorePreferences) =
            DappSessionKeystore(properties, preferences)
    }
}