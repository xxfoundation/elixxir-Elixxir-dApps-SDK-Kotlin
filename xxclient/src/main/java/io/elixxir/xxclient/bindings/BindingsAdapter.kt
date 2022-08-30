package io.elixxir.xxclient.bindings

import io.elixxir.xxclient.backup.Backup
import io.elixxir.xxclient.backup.BackupAdapter
import io.elixxir.xxclient.callbacks.AuthCallbacksAdapter
import io.elixxir.xxclient.callbacks.AuthEventListener
import io.elixxir.xxclient.channel.Channel
import io.elixxir.xxclient.channel.ChannelAdapter
import io.elixxir.xxclient.cmix.CMix
import io.elixxir.xxclient.cmix.CMixAdapter
import io.elixxir.xxclient.dummytraffic.DummyTraffic
import io.elixxir.xxclient.dummytraffic.DummyTrafficAdapter
import io.elixxir.xxclient.e2e.E2e
import io.elixxir.xxclient.e2e.E2eAdapter
import io.elixxir.xxclient.filetransfer.FileTransfer
import io.elixxir.xxclient.filetransfer.FileTransferAdapter
import io.elixxir.xxclient.models.*
import io.elixxir.xxclient.models.BindingsModel.Companion.decode
import io.elixxir.xxclient.models.BindingsModel.Companion.encode
import io.elixxir.xxclient.userdiscovery.UserDiscovery
import io.elixxir.xxclient.userdiscovery.UserDiscoveryAdapter
import io.elixxir.xxclient.utils.*
import bindings.Bindings as CoreBindings

open class BindingsAdapter : Bindings {
    override val defaultCmixParams: CmixParams
        get() = CoreBindings.getDefaultCMixParams()
    override val defaultE2eParams: E2eParams
        get() = CoreBindings.getDefaultE2EParams()
    override val defaultE2eFileTransferParams: E2eFileTransferParams
        get() = CoreBindings.getDefaultE2eFileTransferParams()
    override val defaultFileTransferParams: FileTransferParams
        get() = CoreBindings.getDefaultFileTransferParams()
    override val defaultSingleUseParams: SingleUseParams
        get() = CoreBindings.getDefaultSingleUseParams()
    override val gitVersion: String
        get() = CoreBindings.getGitVersion()
    override val dependencies: String
        get() = CoreBindings.getDependencies()

    override fun downloadAndVerifySignedNdf(
        environmentUrl: String,
        certificate: Certificate
    ): Ndf {
        return CoreBindings.downloadAndVerifySignedNdfWithUrl(environmentUrl, certificate)
    }

    override fun generateSecret(byteLength: Long): Password {
        return CoreBindings.generateSecret(byteLength)
    }

    override fun loadCmix(
        sessionFileDirectory: String,
        sessionPassword: Password,
        cmixParams: CmixParams
    ): CMix {
        return CMixAdapter(
            CoreBindings.loadCmix(sessionFileDirectory, sessionPassword, cmixParams)
        )
    }

    override fun login(
        e2eId: E2eId,
        authCallbacks: AuthEventListener,
        receptionIdentity: ReceptionIdentity,
        e2eParams: E2eParams
    ): E2e {
        return E2eAdapter(
            CoreBindings.login(
                e2eId,
                AuthCallbacksAdapter(authCallbacks),
                encode(receptionIdentity),
                e2eParams
            )
        )
    }

    override fun loginEphemeral(
        e2eId: E2eId,
        authCallbacks: AuthEventListener,
        receptionIdentity: ReceptionIdentity,
        e2eParams: E2eParams
    ): E2e {
        return E2eAdapter(
            CoreBindings.loginEphemeral(
                e2eId,
                AuthCallbacksAdapter(authCallbacks),
                encode(receptionIdentity),
                e2eParams
            )
        )
    }

    override fun getOrCreateUd(
        e2eId: E2eId,
        networkFollowerStatus: NetworkFollowerStatus,
        username: String,
        signature: Signature,
        udCert: CertificateData,
        contact: Contact,
        udIpAddress: String
    ): UserDiscovery {
        return UserDiscoveryAdapter(
            CoreBindings.newOrLoadUd(
                e2eId,
                { networkFollowerStatus.code },
                username,
                signature,
                udCert,
                contact.encoded(),
                udIpAddress
            )
        )
    }

    override fun newUdFromBackup(
        e2eId: E2eId,
        networkFollowerStatus: NetworkFollowerStatus,
        emailFact: Fact,
        phoneFact: Fact,
        udCert: CertificateData,
        contact: Contact,
        udAddress: String
    ): UserDiscovery {
        return UserDiscoveryAdapter(
            CoreBindings.newUdManagerFromBackup(
                e2eId,
                { networkFollowerStatus.code },
                encode(emailFact),
                encode(phoneFact),
                udCert,
                contact.encoded(),
                udAddress
            )
        )
    }

    override fun newDummyTrafficManager(): DummyTraffic {
        TODO()
//        return DummyTrafficAdapter(
//            CoreBindings.newDummyTrafficManager(
//
//            )
//        )
    }

    override fun registerLogger(logLevel: LogLevel, logWriter: LogWriter) {
        CoreBindings.logLevel(logLevel.code)
        CoreBindings.registerLogWriter(logWriter)
    }

    override fun newBackupManager(): Backup {
        TODO()
//        return BackupAdapter(
//            CoreBindings.initializeBackup(
//
//            )
//        )
    }

    override fun initializeBackup(): Backup {
        TODO()
//        return BackupAdapter(
//            CoreBindings.initializeBackup(
//
//            )
//        )
    }

    override fun resumeBackup(): Backup {
        TODO()
//        return BackupAdapter(
//            CoreBindings.resumeBackup(
//
//            )
//        )
    }

    override fun fetchSignedNdf(url: String, cert: String): Ndf {
        return CoreBindings.downloadAndVerifySignedNdfWithUrl(url, cert)
    }

    override fun getReceptionIdentity(key: String, e2eId: E2eId): ReceptionIdentity {
        return decode(
            CoreBindings.loadReceptionIdentity(key, e2eId),
            ReceptionIdentity::class.java
        )
    }

    override fun newFileTransferManager(): FileTransfer {
        TODO()
//        return FileTransferAdapter(
//            CoreBindings.initFileTransfer(
//
//            )
//        )
    }

    override fun getIdFromContact(contactData: ContactData): ByteArray {
        return CoreBindings.getIDFromContact(contactData)
    }

    override fun getPublicKeyFromContact(contactData: ContactData): ByteArray {
        return CoreBindings.getPubkeyFromContact(contactData)
    }

    override fun getFactsFromContact(contactData: ContactData): Fact {
        return decode(
            CoreBindings.getFactsFromContact(contactData),
            Fact::class.java
        )
    }

    override fun setFactsOnContact(contactData: ContactData, fact: Fact): ContactData {
        return CoreBindings.setFactsOnContact(contactData, encode(fact))
    }

    override fun searchUd(): ByteArray {
        TODO()

//        return CoreBindings.searchUD()
    }

    override fun lookupUd(): ByteArray {
        TODO()
//        return CoreBindings.lookupUD()
    }

    override fun newBroadcastChannel(): Channel {
        TODO()
//        return ChannelAdapter(
//            CoreBindings.newBroadcastChannel()
//        )
    }

    override fun storeReceptionIdentity() {
        TODO()
//        CoreBindings.storeReceptionIdentity()
    }

    override fun transmitSingleUse(): ByteArray {
        TODO()
//        return CoreBindings.transmitSingleUse()
    }

    override fun updateCommonErrors(errorsJson: String) {
        CoreBindings.updateCommonErrors(errorsJson)
    }

}