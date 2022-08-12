package io.elixxir.dapp

import io.elixxir.dapp.logger.LoggerConfig
import io.elixxir.dapp.networking.NetworkConfig
import io.elixxir.dapp.session.SessionConfig

interface SdkConfig {
    val androidConfig: AndroidConfig
    val sessionConfig: SessionConfig
    val networkConfig: NetworkConfig
    val loggerConfig: LoggerConfig
}