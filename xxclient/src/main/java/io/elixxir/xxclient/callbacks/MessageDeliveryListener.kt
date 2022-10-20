package io.elixxir.xxclient.callbacks

import bindings.MessageDeliveryCallback

interface MessageDeliveryListener {
    fun onMessageSent(delivered: Boolean, timedOut: Boolean, roundResults: ByteArray?)
}

open class MessageDeliveryCallbackAdapter(
    protected val listener: MessageDeliveryListener
) : MessageDeliveryCallback {

    override fun eventCallback(delivered: Boolean, timedOut: Boolean, roundResults: ByteArray?) {
        listener.onMessageSent(delivered, timedOut, roundResults)
    }
}

