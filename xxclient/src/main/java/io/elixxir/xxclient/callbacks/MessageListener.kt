package io.elixxir.xxclient.callbacks

import bindings.Listener

interface MessageListener {
    val name: String
    fun onMessageReceived(message: ByteArray)
}

open class MessageListenerAdapter(
    protected val listener: MessageListener
) : Listener {
    override fun hear(message: ByteArray?) {
        message?.let {
            listener.onMessageReceived(it)
        }
    }

    override fun name(): String = listener.name
}