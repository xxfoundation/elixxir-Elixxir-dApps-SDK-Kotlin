package io.elixxir.xxclient.callbacks

import bindings.ReceiveFileCallback
import io.elixxir.xxclient.models.InvalidDataException
import java.lang.Exception
typealias Payload = ByteArray

interface FileListener {
    fun onFileReceived(payload: Result<Payload>)
}

open class ReceiveFileCallbackAdapter(
    protected val listener: FileListener
) : ReceiveFileCallback {

    override fun callback(payload: ByteArray?, error: Exception?) {
        val result: Result<Payload> = error?.let {
            Result.failure(it)
        } ?: payload?.let {
            Result.success(payload)
        } ?: Result.failure(InvalidDataException())

        listener.onFileReceived(result)
    }
}