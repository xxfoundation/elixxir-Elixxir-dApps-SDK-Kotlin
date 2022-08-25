package io.elixxir.xxclient.callbacks

import bindings.Listener
import io.elixxir.xxclient.models.BindingsModel.Companion.decode
import io.elixxir.xxclient.models.Message

interface MessageListener {
    val name: String
    fun onMessageReceived(message: Message)
}

open class MessageListenerAdapter(
    protected val listener: MessageListener
) : Listener {
    override fun hear(message: ByteArray?) {
        message?.let {
            listener.onMessageReceived(
                decode(it, Message::class.java)
            )
        }
    }

    override fun name(): String = listener.name
}