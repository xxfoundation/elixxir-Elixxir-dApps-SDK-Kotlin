package io.elixxir.xxclient.channel

import io.elixxir.xxclient.callbacks.BroadcastListener
import io.elixxir.xxclient.callbacks.BroadcastListenerAdapter
import io.elixxir.xxclient.models.BindingsModel.Companion.decode
import io.elixxir.xxclient.models.BroadcastReport
import io.elixxir.xxclient.models.ChannelDef
import io.elixxir.xxclient.utils.Payload
import bindings.Channel as BindingsChannel

interface Channel {
    val maxAsymmetricPayloadSize: Long
    val maxPayloadSize: Long

    fun broadcast(payload: Payload): BroadcastReport
    fun broadcastAsymmetric(payload: Payload, privateKey: ByteArray): BroadcastReport
    fun get(): ChannelDef
    fun listen(channel: Long, listener: BroadcastListener)
    fun stop()
}

open class ChannelAdapter(protected val channel: BindingsChannel) : Channel {
    override val maxAsymmetricPayloadSize: Long
        get() = channel.maxAsymmetricPayloadSize()
    override val maxPayloadSize: Long
        get() = channel.maxPayloadSize()

    override fun broadcast(payload: Payload): BroadcastReport {
        return decode(
            channel.broadcast(payload)
        )
    }

    override fun broadcastAsymmetric(payload: Payload, privateKey: ByteArray): BroadcastReport {
        return decode(
            channel.broadcastAsymmetric(payload, privateKey)
        )
    }

    override fun get(): ChannelDef {
        return decode(
            channel.get()
        )
    }

    override fun listen(channelId: Long, listener: BroadcastListener) {
        channel.listen(
            BroadcastListenerAdapter(listener),
            channelId
        )
    }

    override fun stop() {
        channel.stop()
    }
}