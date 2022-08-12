package io.elixxir.dapp.logger.data

import io.elixxir.dapp.logger.model.LogLevel
import io.elixxir.dapp.logger.model.LoggerConfig

data class NoLogging(
    override val logLevel: LogLevel = LogLevel.None,
    override val logWriter: (String) -> Unit = {}
) : LoggerConfig