package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.bindings.model.*
import io.elixxir.dapp.bindings.model.E2eParams
import io.elixxir.dapp.bindings.model.ReceptionIdentity
import io.elixxir.dapp.logger.model.LoggerConfig
import io.elixxir.dapp.session.model.SessionPassword
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

    override fun loadCmix(): Cmix {
        TODO("Not yet implemented")
    }

    override fun login(): E2e {
        TODO("Not yet implemented")
    }

    override fun getOrCreateUd(): UserDiscovery {
        TODO("Not yet implemented")
    }

    override fun newUdFromBackup(): UserDiscovery {
        TODO("Not yet implemented")
    }

    override fun newDummyTrafficManager(): DummyTrafficManager {
        TODO("Not yet implemented")
    }

    override fun registerLogger(loggerConfig: LoggerConfig) {
        TODO("Not yet implemented")
    }

    override fun initializeBackup(): Backup {
        TODO("Not yet implemented")
    }

    override fun resumeBackup(): Backup {
        TODO("Not yet implemented")
    }

    override fun fetchSignedNdf(): SignedNdf {
        TODO("Not yet implemented")
    }

    override fun getReceptionIdentity(): ReceptionIdentity {
        TODO("Not yet implemented")
    }
}