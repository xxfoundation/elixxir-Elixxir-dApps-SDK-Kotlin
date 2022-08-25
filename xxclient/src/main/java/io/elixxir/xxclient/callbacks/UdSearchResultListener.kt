package io.elixxir.xxclient.callbacks

import bindings.UdSearchCallback
import io.elixxir.xxclient.utils.ContactData
import io.elixxir.xxclient.utils.parse
import java.lang.Exception

interface UdSearchResultListener {
    fun onResponse(result: Result<ContactData>)
}

open class UdSearchCallbackAdapter(
    protected val listener: UdSearchResultListener
) : UdSearchCallback {
    override fun callback(contactData: ByteArray?, error: Exception?) {
        listener.onResponse(
            parse(contactData, error, ContactData::class.java)
        )
    }
}