package io.elixxir.xxclient.callbacks

import bindings.LogWriter

interface LogEventListener {
    fun onEvent(message: String)
}

class LogWriterAdapter(
    protected val listener: LogEventListener
) : LogWriter {
    override fun log(message: String?) {
        message?.let {
            listener.onEvent(it)
        }
    }
}