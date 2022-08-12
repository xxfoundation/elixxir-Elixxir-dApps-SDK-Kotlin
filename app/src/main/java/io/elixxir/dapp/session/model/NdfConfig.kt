package io.elixxir.dapp.session.model

import androidx.annotation.RawRes
import io.elixxir.dapp.R
import io.elixxir.dapp.model.CriticalRemoteDataStrategy
import io.elixxir.dapp.model.RetryStrategy

interface NdfConfig {
    val environment: Environment get() = Environment.MainNet
    val retryStrategy: RetryStrategy get() = CriticalRemoteDataStrategy()
}

internal abstract class NdfSettings : NdfConfig {
    abstract val ndfUrl: String
    abstract val certificateRef: Int

    companion object {
        fun create(config: NdfConfig): NdfSettings {
            return when (config.environment) {
                Environment.MainNet -> MainNet(config.retryStrategy)
                Environment.ReleaseNet -> ReleaseNet(config.retryStrategy)
            }
        }
    }
}

internal data class MainNet(
    override val retryStrategy: RetryStrategy,
    override val ndfUrl: String =
        "https://elixxir-bins.s3.us-west-1.amazonaws.com/ndf/mainnet.json",
    @RawRes
    override val certificateRef: Int = R.raw.mainnet,
) : NdfSettings()

internal data class ReleaseNet(
    override val retryStrategy: RetryStrategy,
    override val ndfUrl: String =
        "https://elixxir-bins.s3.us-west-1.amazonaws.com/ndf/release.json",
    @RawRes
    override val certificateRef: Int = R.raw.release
) : NdfSettings()