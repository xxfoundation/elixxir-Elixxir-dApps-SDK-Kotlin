package io.elixxir.dapp.session.data

import android.app.Application
import bindings.AuthCallbacks
import timber.log.Timber
import java.io.File
import bindings.Bindings
import bindings.Cmix
import io.elixxir.dapp.session.models.*
import kotlinx.coroutines.*

interface Session {
    val sessionFolder: File
}

class DAppSession private constructor(
    private val app: Application,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : Session {
    override val sessionFolder: File = File(app.filesDir, "cmix/session")

    private fun createSessionFolder(): File {
        return sessionFolder.apply {
            if (exists()) {
                Timber.v("Bindings folder from previous installation was found.")
                Timber.v("It contains ${listFiles()?.size ?: 0} files.")
                Timber.v("Deleting!")
                deleteRecursively()
            }
            mkdir()
            Timber.v("Bindings folder was successfully created at: $absolutePath")
        }
    }

    private fun newCmix(
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

    private suspend fun loadCmix(
        sessionFolderPath: String,
        sessionPassword: SessionPassword,
        cmixParamsJson: E2eParams
    ): CmixMediator = withContext(defaultDispatcher) {
        CmixMediator(
            Bindings.loadCmix(sessionFolderPath, sessionPassword.value, cmixParamsJson.value)
        )
    }

    private fun login(
        cmixId: Long,
        authCallbacks: AuthCallbacks,
        identity: ReceptionIdentity,
        e2eParamsJson: E2eParams
    ): E2eMediator {
        return E2eMediator(
            Bindings.login(cmixId, authCallbacks, identity.value, e2eParamsJson.value)
        )
    }

    private fun createIdentity(): ReceptionIdentity {
        return ReceptionIdentity(
            Cmix().makeReceptionIdentity()
        )
    }

    companion object {
        internal fun newInstance(
            app: Application,
            environment: Environment,
            defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
        ) = DAppSession(app, defaultDispatcher)
    }
}