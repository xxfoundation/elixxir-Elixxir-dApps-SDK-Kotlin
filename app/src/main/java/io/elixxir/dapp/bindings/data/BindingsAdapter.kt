package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.bindings.model.*
import io.elixxir.dapp.bindings.model.E2eParams
import io.elixxir.dapp.bindings.model.ReceptionIdentity
import io.elixxir.dapp.logger.model.LoggerConfig
import io.elixxir.dapp.network.repository.Ndf
import io.elixxir.dapp.session.model.SessionPassword
import io.elixxir.dapp.util.toBase64String
import bindings.Bindings as CoreBindings

internal class BindingsAdapter: Bindings {
    override val defaultE2eParams: E2eParams
        get() = E2eParams(CoreBindings.getDefaultE2EParams())
    override val defaultCmixParams: CmixParams
        get() = CmixParams(CoreBindings.getDefaultCMixParams())
    override val defaultFileTransferParams: FileTransferParams
        get() = FileTransferParams(CoreBindings.getDefaultFileTransferParams())
    override val gitVersion: String
        get() = CoreBindings.getGitVersion()

    override fun generateSecret(byteLength: Long): SessionPassword {
        return SessionPassword(CoreBindings.generateSecret(byteLength))
    }

    override fun loadCmix(
        sessionFileDirectory: String,
        sessionPassword: SessionPassword,
        cmixParams: CmixParams
    ): Cmix {
        return CmixAdapter(
            CoreBindings.loadCmix(
                sessionFileDirectory,
                sessionPassword.value,
                cmixParams.value
            )
        )
    }

    override fun login(
        cmixId: CmixId,
        authCallbacks: AuthCallbacksAdapter,
        receptionIdentity: ReceptionIdentity,
        e2eParams: E2eParams
    ): E2e {
        // TODO: Use factory method to get E2E implementation
        return E2eAdapter(
            CoreBindings.login(
                cmixId.value,
                authCallbacks.value,
                receptionIdentity.value,
                e2eParams.value
            )
        )
    }

    override fun getOrCreateUd(
        e2eId: E2eId,
        networkFollowerStatus: NetworkFollowerStatus,
        username: String,
        signature: RegistrationValidationSignature,
        udCert: UdCertificate,
        contact: Contact,
        udIpAddress: UdIpAddress
    ): UserDiscovery {
        // TODO: Use factory method to get UD implementation
        return UserDiscoveryAdapter(
            CoreBindings.newOrLoadUd(
                e2eId.value,
                { networkFollowerStatus.code },
                username,
                signature.value,
                udCert.value,
                contact.value,
                udIpAddress.value
            )
        )
    }

    override fun newUdFromBackup(
        e2eId: E2eId,
        networkFollowerStatus: NetworkFollowerStatus,
        emailFact: Fact,
        phoneFact: Fact,
        udCert: UdCertificate,
        contact: Contact,
        udIpAddress: UdIpAddress
    ): UserDiscovery {
        // TODO: Use factory method to get UD implementation
        return UserDiscoveryAdapter(
            CoreBindings.newUdManagerFromBackup(
                e2eId.value,
                { networkFollowerStatus.code },
                emailFact.value,
                phoneFact.value,
                udCert.value,
                contact.value,
                udIpAddress.value
            )
        )
    }

    override fun newDummyTrafficManager(): DummyTrafficManager {
        TODO("Not yet implemented")
    }

    override fun registerLogger(loggerConfig: LoggerConfig) {
        with (loggerConfig) {
            CoreBindings.logLevel(logLevel.code)
            CoreBindings.registerLogWriter(logWriter)
        }
    }

    override fun initializeBackup(): Backup {
        TODO("Not yet implemented")
    }

    override fun resumeBackup(): Backup {
        TODO("Not yet implemented")
    }

    override fun fetchSignedNdf(
        url: String,
        cert: String
    ): Ndf {
        return CoreBindings.downloadAndVerifySignedNdfWithUrl(url, cert).toBase64String()
    }

    override fun getReceptionIdentity(
        key: SessionPassword,
        cmixId: CmixId
    ): ReceptionIdentity {
        return ReceptionIdentity(
            CoreBindings.loadReceptionIdentity(
                key.value.toBase64String(),
                cmixId.value
            )
        )
    }
}