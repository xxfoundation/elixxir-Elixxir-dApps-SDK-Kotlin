package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.bindings.model.*
import io.elixxir.dapp.bindings.model.E2eParams
import io.elixxir.dapp.logger.model.LoggerConfig
import io.elixxir.dapp.session.model.SessionPassword

internal interface Bindings {
    val defaultE2eParams: E2eParams
    val defaultCmixParams: CmixParams
    val defaultFileTransferParams: FileTransferParams
    val gitVersion: String

    fun generateSecret(byteLength: Long): SessionPassword

    fun loadCmix(): Cmix

    fun login(): E2e

    fun getOrCreateUd(): UserDiscovery

    fun newUdFromBackup(): UserDiscovery

    fun newDummyTrafficManager(): DummyTrafficManager

    fun registerLogger(loggerConfig: LoggerConfig)

    fun initializeBackup(): Backup

    fun resumeBackup(): Backup

    fun fetchSignedNdf(): SignedNdf

    fun getReceptionIdentity(): ReceptionIdentity
}

