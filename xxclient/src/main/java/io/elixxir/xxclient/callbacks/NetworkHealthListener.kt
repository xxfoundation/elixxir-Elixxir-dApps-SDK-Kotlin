package io.elixxir.xxclient.callbacks

import bindings.NetworkHealthCallback

interface NetworkHealthListener {
    fun onHealthUpdate(isHealthy: Boolean)
}

open class HealthCallbackAdapter(
    protected val listener: NetworkHealthListener
) : NetworkHealthCallback {
    override fun callback(isHealthy: Boolean) {
        listener.onHealthUpdate(isHealthy)
    }
}