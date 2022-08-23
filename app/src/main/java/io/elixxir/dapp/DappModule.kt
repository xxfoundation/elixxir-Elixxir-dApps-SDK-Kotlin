package io.elixxir.dapp

import io.elixxir.dapp.android.model.AndroidConfig
import io.elixxir.dapp.api.model.*
import io.elixxir.dapp.logger.data.Logger
import io.elixxir.dapp.api.model.CommonProperties
import io.elixxir.dapp.api.model.DappConfig

/**
 * Singleton entry point to modules provided by the dApp SDK.
 */
class DappModule private constructor(
    config: DappConfig,
    logger: Logger = Logger.newInstance(config.loggerConfig)
) : DappApi,
    AndroidConfig by config.androidConfig,
    Logger by logger,
    CommonProperties
{
    override val accountApi: AccountApi
        get() = TODO("Not yet implemented")
    override val directoryApi: DirectoryApi
        get() = TODO("Not yet implemented")
    override val messagesApi: MessagesApi
        get() = TODO("Not yet implemented")
    override val networkApi: NetworkApi
        get() = TODO("Not yet implemented")
    override val requestsApi: RequestsApi
        get() = TODO("Not yet implemented")
    override val groupsApi: GroupsApi
        get() = TODO("Not yet implemented")

    companion object {
        @Volatile
        private var instance: DappModule? = null

        fun getInstance(config: DappConfig): DappModule {
            return instance ?: DappModule(config).apply {
                instance = this
            }
        }
    }
}