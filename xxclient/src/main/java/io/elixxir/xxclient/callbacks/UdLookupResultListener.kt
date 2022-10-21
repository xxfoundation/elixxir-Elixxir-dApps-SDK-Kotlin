package io.elixxir.xxclient.callbacks

import bindings.UdLookupCallback
import io.elixxir.xxclient.utils.*
import java.lang.Exception

interface UdLookupResultListener {
    fun onResponse(response: Result<List<ContactData>>)
}

open class UdLookupCallbackAdapter(
    protected val listener: UdLookupResultListener
) : UdLookupCallback {
    override fun callback(contacts: ByteArray?, error: Exception?) {
        val contactsResult = parseDataArray(
            contacts,
            error
        )

        listener.onResponse(
            nonNullResultOf {
                contactsResult.getOrThrow()
            }
        )
    }
}