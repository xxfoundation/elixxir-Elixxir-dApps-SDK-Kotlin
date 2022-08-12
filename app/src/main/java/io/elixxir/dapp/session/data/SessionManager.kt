package io.elixxir.dapp.session.data

import android.content.Context
import bindings.AuthCallbacks
import java.io.File
import bindings.Bindings
import bindings.Cmix
import io.elixxir.dapp.DappSdk.Companion.context
import io.elixxir.dapp.DappSdk.Companion.defaultDispatcher
import io.elixxir.dapp.DappSdk.Companion.logger
import io.elixxir.dapp.logger.data.Logger
import io.elixxir.dapp.session.model.*
import kotlinx.coroutines.*

/**
 * Establishes & persists an identity to perform Cmix operations.
 * Only needs to be created once per app installation.
 */
interface SessionManager {
    val sessionFolder: File
}

internal class DappSessionManager private constructor(
    logger: Logger,
    private val context: () -> Context,
    private val dispatcher: CoroutineDispatcher
) : SessionManager, Logger by logger {

    override val sessionFolder: File get() {
        return try {
            File(context().filesDir, "cmix/session")
        } catch (e: Exception) {
            logFatal(e.message ?: "Couldn't access filesDir.")
            throw e
        }
    }

    fun createSessionFolder(): File {
        return sessionFolder.apply {
            if (exists()) {
                log("Session folder from previous installation was found.")
                log("It contains ${listFiles()?.size ?: 0} files.")
                log("Deleting!")
                deleteRecursively()
            }
            mkdir()
            log("Session folder successfully created at: $absolutePath")
        }
    }

    fun newCmix(
        ndfJson: String,
        sessionFolderPath: String,
        sessionPassword: SessionPassword,
        registrationCode: String
    ) {
        Bindings.newCmix(
            ndfJson,
            sessionFolderPath,
            sessionPassword.value,
            registrationCode
        )
    }

    suspend fun loadCmix(
        sessionFolderPath: String,
        sessionPassword: SessionPassword,
        cmixParamsJson: E2eParams
    ): CmixMediator = withContext(dispatcher) {
        CmixMediator(
            Bindings.loadCmix(sessionFolderPath, sessionPassword.value, cmixParamsJson.value)
        )
    }

    fun login(
        cmixId: Long,
        authCallbacks: AuthCallbacks,
        identity: ReceptionIdentity,
        e2eParamsJson: E2eParams
    ): E2eMediator {
        return E2eMediator(
            Bindings.login(cmixId, authCallbacks, identity.value, e2eParamsJson.value)
        )
    }

    fun createIdentity(): ReceptionIdentity {
        return ReceptionIdentity(
            Cmix().makeReceptionIdentity()
        )
    }

    companion object {
        internal fun newInstance(): SessionManager {
            return DappSessionManager(
                logger = logger,
                context = context,
                dispatcher = defaultDispatcher
            )
        }
    }
}