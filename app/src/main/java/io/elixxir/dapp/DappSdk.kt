package io.elixxir.dapp

import android.content.Context
import io.elixxir.dapp.android.model.AndroidConfig
import io.elixxir.dapp.logger.data.Logger
import io.elixxir.dapp.logger.model.LoggerConfig
import io.elixxir.dapp.model.DappConfig
import kotlinx.coroutines.CoroutineDispatcher

interface GlobalConfig : AndroidConfig, LoggerConfig

/**
 * Singleton entry point to modules provided by the dApp SDK.
 */
class DappSdk private constructor(
    config: DappConfig
) : GlobalConfig,
    AndroidConfig by config.androidConfig,
    LoggerConfig by config.loggerConfig
{


    companion object {
        internal val logger: Logger by lazy {
            Logger.newInstance(instance!!)
        }
        internal val defaultDispatcher: CoroutineDispatcher by lazy {
            instance!!.defaultDispatcher
        }
        internal val context: () -> Context get() = instance!!.context

        @Volatile
        private var instance: DappSdk? = null

        fun getInstance(config: DappConfig): DappSdk {
            return instance ?: DappSdk(config).apply {
                instance = this
            }
        }
    }
}