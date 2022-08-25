package io.elixxir.xxclient.callbacks

import bindings.UdLookupCallback
import io.elixxir.xxclient.utils.parse
import java.lang.Exception

typealias ContactData = ByteArray

interface UdLookupListener {
    fun onResponse(response: Result<ContactData>)
}

open class UdLookupCallbackAdapter(
    protected val listener: UdLookupListener
) : UdLookupCallback {

    override fun callback(contactData: ByteArray?, error: Exception?) {
        listener.onResponse(
            parse(contactData, error, ByteArray::class.java)
        )
    }
}