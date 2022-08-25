package io.elixxir.xxclient.connection

import bindings.Connection as ConnectionBindings
import io.elixxir.xxclient.callbacks.MessageListener
import io.elixxir.xxclient.callbacks.MessageListenerAdapter
import io.elixxir.xxclient.utils.Payload

interface Connection{
    val isAuthenticated: Boolean
    val id: Long
    val partner: ByteArray

    fun registerListener(messageType: Long, listener: MessageListener)
    fun send(messageType: Long, payload: Payload)
    fun close()
}

open class ConnectionAdapter(
    private val authenticated: Boolean,
    protected val connection: ConnectionBindings
) : Connection {
    override val isAuthenticated: Boolean
        get() = authenticated
    override val id: Long
        get() = connection.id
    override val partner: ByteArray
        get() = connection.partner

    override fun registerListener(messageType: Long, listener: MessageListener) {
        connection.registerListener(
            messageType,
            MessageListenerAdapter(listener)
        )
    }

    override fun send(messageType: Long, payload: Payload) {
        connection.sendE2E(messageType, payload)
    }

    override fun close() {
        connection.close()
    }
}