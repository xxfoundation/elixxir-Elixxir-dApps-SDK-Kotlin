package io.elixxir.dapp.session.data

import android.content.Context
import androidx.annotation.RawRes
import bindings.Bindings
import io.elixxir.dapp.DappSdk.Companion.context
import io.elixxir.dapp.DappSdk.Companion.defaultDispatcher
import io.elixxir.dapp.DappSdk.Companion.logger
import io.elixxir.dapp.logger.data.Logger
import io.elixxir.dapp.model.RetryStrategy
import io.elixxir.dapp.session.model.NdfSettings
import kotlinx.coroutines.*
typealias Ndf = String

internal interface NdfDataSource {
    suspend fun fetchNdf(): Ndf
}

internal class RemoteNdfDataSource constructor(
    logger: Logger,
    private val context: () -> Context,
    private val dispatcher: CoroutineDispatcher,
    private val settings: NdfSettings
) : NdfDataSource,
    Logger by logger,
    RetryStrategy by settings.retryStrategy
{

    override suspend fun fetchNdf(): Ndf {
        return retryFetchNdf()
    }

    private suspend fun retryFetchNdf(retries: Int = 0): Ndf = withContext(dispatcher) {
        val ndf = downloadAndVerifySignedNdfWithUrl(
            settings.ndfUrl,
            readCertificate(settings.certificateRef)
        )

        if (ndf.isEmpty() && retries <= maxRetries) {
            ensureActive()
            delay(retryDelayMs)
            retryFetchNdf(retries + 1)
        } else {
            ndf
        }
    }

    private fun downloadAndVerifySignedNdfWithUrl(
        url: String,
        certificate: String
    ): Ndf = String(Bindings.downloadAndVerifySignedNdfWithUrl(url, certificate))

    private fun readCertificate(@RawRes certRef: Int): String {
        val reader= context().resources.openRawResource(certRef)
            .bufferedReader()
        return reader.use { reader.readText() }
    }

    companion object {
        internal fun newInstance(settings: NdfSettings): NdfDataSource {
            return with(settings) {
                RemoteNdfDataSource(
                    logger = logger,
                    context = context,
                    dispatcher = defaultDispatcher,
                    settings = this
                )
            }
        }
    }
}