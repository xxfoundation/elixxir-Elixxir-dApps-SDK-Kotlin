package io.elixxir.dapp.logger.model

interface LoggerConfig {
    val logLevel: LogLevel
    val logWriter: (String) -> Unit
}