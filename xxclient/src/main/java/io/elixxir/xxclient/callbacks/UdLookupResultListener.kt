package io.elixxir.xxclient.callbacks

import bindings.UdLookupCallback
import io.elixxir.xxclient.utils.ContactData
import io.elixxir.xxclient.utils.parse
import java.lang.Exception

interface UdLookupResultListener {
    fun onResponse(response: Result<ContactData>)
}

open class UdLookupCallbackAdapter(
    protected val listener: UdLookupResultListener
) : UdLookupCallback {

    override fun callback(contactData: ByteArray?, error: Exception?) {
        listener.onResponse(
            parse(contactData, error, ByteArray::class.java)
        )
    }
}