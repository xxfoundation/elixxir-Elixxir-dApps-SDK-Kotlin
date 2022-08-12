package io.elixxir.dapp.logger.data

import io.elixxir.dapp.logger.model.LoggerConfig
import io.elixxir.dapp.logger.model.LogLevel
import timber.log.Timber

internal data class Timber(
    override val logLevel: LogLevel = LogLevel.Debug,
    override val logWriter: (String) -> Unit = { Timber.v(it) }
) : LoggerConfig
