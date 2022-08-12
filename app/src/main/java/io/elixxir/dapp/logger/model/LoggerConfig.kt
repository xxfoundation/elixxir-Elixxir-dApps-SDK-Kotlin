package io.elixxir.dapp.logger.model

/**
 * Responsible for routing helpful output of [logLevel] severity or greater
 * to the provided [logWriter].
 */
interface LoggerConfig {
    val logLevel: LogLevel
    val logWriter: (String) -> Unit
}