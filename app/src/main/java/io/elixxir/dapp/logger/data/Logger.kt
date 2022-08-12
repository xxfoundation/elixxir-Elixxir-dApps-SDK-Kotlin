package io.elixxir.dapp.logger.data

import io.elixxir.dapp.logger.model.LoggerConfig
import io.elixxir.dapp.logger.model.LogLevel

internal interface Logger {
    val config: LoggerConfig

    fun logFatal(message: String) = log(message, LogLevel.Fatal)
    fun logWarn(message: String) = log(message, LogLevel.Warn)
    fun log(message: String, level: LogLevel = LogLevel.Debug): () -> Unit = {
        if (config.logLevel <= level) {
            config.logWriter(message)
        }
    }

    companion object {
        fun newInstance(config: LoggerConfig) = object : Logger {
            override val config: LoggerConfig = config
        }
    }
}