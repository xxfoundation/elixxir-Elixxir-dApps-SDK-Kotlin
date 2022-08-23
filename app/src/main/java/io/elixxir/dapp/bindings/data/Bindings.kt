package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.bindings.model.*
import io.elixxir.dapp.bindings.model.E2eParams
import io.elixxir.dapp.logger.model.LoggerConfig
import io.elixxir.dapp.network.repository.Ndf
import io.elixxir.dapp.session.model.SessionPassword

internal interface Bindings {
    val defaultE2eParams: E2eParams
    val defaultCmixParams: CmixParams
    val defaultFileTransferParams: FileTransferParams
    val gitVersion: String

    fun generateSecret(byteLength: Long): SessionPassword

    fun loadCmix(
        sessionFileDirectory: String,
        sessionPassword: SessionPassword,
        cmixParams: CmixParams
    ): Cmix

    fun login(
        cmixId: CmixId,
        authCallbacks: AuthCallbacksAdapter,
        receptionIdentity: ReceptionIdentity,
        e2eParams: E2eParams
    ): E2e

    fun getOrCreateUd(
        e2eId: E2eId,
        networkFollowerStatus: NetworkFollowerStatus,
        username: String,
        signature: RegistrationValidationSignature,
        udCert: UdCertificate,
        contact: Contact,
        udIpAddress: UdIpAddress
    ): UserDiscovery

    fun newUdFromBackup(
        e2eId: E2eId,
        networkFollowerStatus: NetworkFollowerStatus,
        emailFact: Fact,
        phoneFact: Fact,
        udCert: UdCertificate,
        contact: Contact,
        udAddress: UdIpAddress
    ): UserDiscovery

    fun newDummyTrafficManager(): DummyTrafficManager

    fun registerLogger(loggerConfig: LoggerConfig)

    fun initializeBackup(): Backup

    fun resumeBackup(): Backup

    fun fetchSignedNdf(
        url: String,
        cert: String
    ): Ndf

    fun getReceptionIdentity(
        key: SessionPassword,
        cmixId: CmixId
    ): ReceptionIdentity
}

