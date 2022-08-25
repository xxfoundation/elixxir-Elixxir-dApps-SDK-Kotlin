package io.elixxir.xxclient.callbacks

import bindings.ReceiveFileCallback
import io.elixxir.xxclient.utils.Payload
import io.elixxir.xxclient.utils.parse
import java.lang.Exception


interface FileListener {
    fun onFileReceived(payload: Result<Payload>)
}

open class ReceiveFileCallbackAdapter(
    protected val listener: FileListener
) : ReceiveFileCallback {

    override fun callback(payload: ByteArray?, error: Exception?) {
        listener.onFileReceived(
            parse(payload, error, Payload::class.java)
        )
    }
}