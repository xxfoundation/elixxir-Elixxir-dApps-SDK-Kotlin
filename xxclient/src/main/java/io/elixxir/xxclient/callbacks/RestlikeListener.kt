package io.elixxir.xxclient.callbacks

import bindings.RestlikeCallback
import io.elixxir.xxclient.models.RestlikeMessage
import io.elixxir.xxclient.utils.parse
import java.lang.Exception

interface RestlikeListener {
    fun handle(data: Result<RestlikeMessage>)
}

open class RestlikeCallbackAdapter(
    protected val listener: RestlikeListener
) : RestlikeCallback {

    override fun callback(payload: ByteArray?, error: Exception?) {
        listener.handle(
            parse(payload, error, RestlikeMessage::class.java)
        )
    }
}