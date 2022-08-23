package io.elixxir.xxclient.callbacks

import io.elixxir.xxclient.models.BindingsModel
import java.lang.Exception
import bindings.BroadcastListener as BroadcastListenerBindings
import io.elixxir.xxclient.models.BroadcastMessage

interface BroadcastListener {
    fun handle(result: Result<BroadcastMessage>)
}

open class BroadCastListenerAdapter(
    protected val listener: BroadcastListener
) : BroadcastListener by listener, BroadcastListenerBindings {
    override fun callback(data: ByteArray?, error: Exception?) {
        val result: Result<BroadcastMessage> = error?.let {
            Result.failure(it)
        } ?: data?.let {
            Result.success(BindingsModel.decode(it, BroadcastMessage::class.java))
        } ?: Result.failure(Throwable("Invalid data"))
    }
}