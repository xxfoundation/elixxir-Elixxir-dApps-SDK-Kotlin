package io.elixxir.xxclient.callbacks

import java.lang.Exception
import bindings.BroadcastListener as BroadcastListenerBindings
import io.elixxir.xxclient.models.BroadcastMessage
import io.elixxir.xxclient.utils.parseModel

interface BroadcastListener {
    fun onBroadcast(result: Result<BroadcastMessage>)
}

open class BroadcastListenerAdapter(
    protected val listener: BroadcastListener
) : BroadcastListener by listener, BroadcastListenerBindings {

    override fun callback(data: ByteArray?, error: Exception?) {
        listener.onBroadcast(
            parseModel(data, error)
        )
    }
}