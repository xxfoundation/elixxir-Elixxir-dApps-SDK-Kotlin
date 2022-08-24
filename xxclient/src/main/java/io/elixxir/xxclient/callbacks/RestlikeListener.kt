package io.elixxir.xxclient.callbacks

import bindings.RestlikeCallback
import io.elixxir.xxclient.models.BindingsModel.Companion.decode
import io.elixxir.xxclient.models.InvalidDataException
import io.elixxir.xxclient.models.RestlikeMessage
import java.lang.Exception

interface RestlikeListener {
    fun onReceived(data: Result<RestlikeMessage>)
}

open class RestlikeCallbackAdapter(
    protected val listener: RestlikeListener
) : RestlikeCallback {

    override fun callback(restlikeMessage: ByteArray?, error: Exception?) {
        val data = error?.let {
            Result.failure(it)
        } ?: restlikeMessage?.let {
            Result.success(
                decode(it, RestlikeMessage::class.java)
            )
        } ?: Result.failure(InvalidDataException())

        listener.onReceived(data)
    }
}