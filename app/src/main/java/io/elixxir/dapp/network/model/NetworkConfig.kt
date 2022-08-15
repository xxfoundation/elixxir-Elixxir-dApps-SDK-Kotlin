package io.elixxir.dapp.network.model

import androidx.annotation.RawRes
import io.elixxir.dapp.R
import io.elixxir.dapp.api.model.CriticalRemoteDataStrategy
import io.elixxir.dapp.api.model.RetryStrategy

interface NetworkConfig {
    val environment: Environment get() = Environment.MainNet
    val retryStrategy: RetryStrategy get() = CriticalRemoteDataStrategy()
}

internal abstract class NdfSettings : NetworkConfig {
    abstract val ndfUrl: String
    abstract val certificateRef: Int

    companion object {
        fun create(config: NetworkConfig): NdfSettings {
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