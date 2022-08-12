package io.elixxir.dapp.model

import io.elixxir.dapp.android.model.AndroidConfig
import io.elixxir.dapp.logger.model.LoggerConfig
import io.elixxir.dapp.session.model.NdfConfig

/**
 * Describes configurable options, and satisfies dependencies for,
 * modules exposed by the dApps Kotlin SDK.
 */
interface DappConfig {
    val androidConfig: AndroidConfig
    val loggerConfig: LoggerConfig
    val ndfConfig: NdfConfig
}