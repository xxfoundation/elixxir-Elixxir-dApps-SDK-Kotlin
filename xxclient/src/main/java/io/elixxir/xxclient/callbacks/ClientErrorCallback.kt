package io.elixxir.xxclient.callbacks

import io.elixxir.xxclient.models.ClientError
import bindings.ClientError as ClientErrorBindings

interface ClientErrorCallback {
    fun onError(error: ClientError)
}

open class ClientErrorCallbackAdapter(
    protected val callback: ClientErrorCallback
) : ClientErrorCallback by callback,
    ClientErrorBindings
{

    override fun report(source: String?, message: String?, trace: String?) {
        callback.onError(ClientError(source, message, trace))
    }
}