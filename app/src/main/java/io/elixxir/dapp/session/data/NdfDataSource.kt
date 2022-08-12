package io.elixxir.dapp.session.data

import android.app.Application
import bindings.Bindings
import io.elixxir.dapp.R
import io.elixxir.dapp.session.models.Environment
import kotlinx.coroutines.*
typealias Ndf = String

interface NdfDataSource {
    suspend fun fetchNdf(): Ndf
}

class RemoteNdfDataSource internal constructor(
    private val app: Application,
    private val environment: Environment,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NdfDataSource {

    override suspend fun fetchNdf(): Ndf {
        return retryFetchNdf()
    }

    private suspend fun retryFetchNdf(retries: Int = 0): Ndf = withContext(defaultDispatcher) {
        val ndf = when (environment) {
            Environment.MainNet -> {
                downloadAndVerifySignedNdfWithUrl(
                    NDF_URL_MAINNET,
                    certificateFor(Environment.MainNet)
                )
            }
            Environment.ReleaseNet -> {
                downloadAndVerifySignedNdfWithUrl(
                    NDF_URL_RELEASE,
                    certificateFor(Environment.ReleaseNet)
                )
            }
        }

        if (ndf.isEmpty() && retries <= NDF_MAX_RETRIES) {
            ensureActive()
            delay(NDF_POLL_INTERVAL_MS)
            retryFetchNdf(retries + 1)
        } else {
            ndf
        }
    }

    private fun downloadAndVerifySignedNdfWithUrl(
        url: String,
        certificate: String
    ): Ndf = String(Bindings.downloadAndVerifySignedNdfWithUrl(url, certificate))

    private fun certificateFor(environment: Environment): String {
        val certFile: Int = when (environment) {
            Environment.MainNet -> R.raw.mainnet
            Environment.ReleaseNet -> R.raw.release
        }
        val reader= app.resources.openRawResource(certFile)
            .bufferedReader()
        return reader.use { reader.readText() }
    }

    companion object {
        private const val NDF_URL_MAINNET =
            "https://elixxir-bins.s3.us-west-1.amazonaws.com/ndf/mainnet.json"
        private const val NDF_URL_RELEASE =
            "https://elixxir-bins.s3.us-west-1.amazonaws.com/ndf/release.json"
        private const val NDF_MAX_RETRIES = 29
        private const val NDF_POLL_INTERVAL_MS = 1000L
    }
}