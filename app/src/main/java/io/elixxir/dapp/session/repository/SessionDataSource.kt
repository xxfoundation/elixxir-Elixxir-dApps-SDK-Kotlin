package io.elixxir.dapp.session.repository

import java.io.File
import io.elixxir.dapp.api.model.CommonProperties
import kotlinx.coroutines.*

/**
 * Responsible for creation, deletion & storage of folder used to persist
 * Cmix session.
 */
internal interface SessionDataSource {
    val sessionFolder: File

    fun doesSessionExist(): Boolean

    /**
     * Only needs to be created once per app installation.
     * Return the [sessionFolder] if successful.
     */
    suspend fun createSession(): Result<File>

    /**
     * Should be called during account deletion.
     */
    suspend fun deleteSession(): Result<Unit>

    /**
     * Restore session
     */
    suspend fun restoreSession(): Result<Unit>
}

internal class LocalSessionDataSource private constructor(
    properties: CommonProperties
) : SessionDataSource, CommonProperties by properties {

    override val sessionFolder: File get() {
        return try {
            File(context().filesDir, "cmix/session")
        } catch (e: Exception) {
            logFatal(e.message ?: "Couldn't access filesDir.")
            throw e
        }
    }

    override fun doesSessionExist(): Boolean = sessionFolder.exists()

    override suspend fun createSession(): Result<File> = withContext(dispatcher) {
        try {
            Result.success(createSessionFolder())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun createSessionFolder(): File {
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

    override suspend fun deleteSession(): Result<Unit> = withContext(dispatcher) {
        try {
            if (sessionFolder.deleteRecursively()) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete all files."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun restoreSession(): Result<Unit> {
        TODO("Not yet implemented")
    }

    companion object {
        internal fun newInstance(properties: CommonProperties) = LocalSessionDataSource(properties)
    }
}