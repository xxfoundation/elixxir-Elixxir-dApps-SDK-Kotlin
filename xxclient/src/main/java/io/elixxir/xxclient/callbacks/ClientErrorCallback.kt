package io.elixxir.xxclient.callbacks

import io.elixxir.xxclient.models.ClientError

interface ClientErrorCallback {
    fun handle(error: ClientError)
}

open class ClientErrorCallbackAdapter(
    protected val callback: ClientErrorCallback
) : ClientErrorCallback by callback, bindings.ClientError {
    
    override fun report(source: String?, message: String?, trace: String?) {
        callback.handle(ClientError(source, message, trace))
    }
}