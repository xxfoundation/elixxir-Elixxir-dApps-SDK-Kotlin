package io.elixxir.dapp.logger

import io.elixxir.dapp.logger.models.LogLevel

interface LoggerConfig {
    val logLevel: LogLevel
    val logWriter: (String) -> Unit
}