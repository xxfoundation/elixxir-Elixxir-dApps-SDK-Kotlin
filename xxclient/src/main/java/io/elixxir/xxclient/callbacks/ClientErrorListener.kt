package io.elixxir.xxclient.callbacks

import io.elixxir.xxclient.models.ClientError
import bindings.ClientError as ClientErrorBindings

interface ClientErrorListener {
    fun onError(error: ClientError)
}

open class ClientErrorCallbackAdapter(
    protected val listener: ClientErrorListener
) : ClientErrorListener by listener,
    ClientErrorBindings
{

    override fun report(source: String?, message: String?, trace: String?) {
        listener.onError(ClientError(source, message, trace))
    }
}