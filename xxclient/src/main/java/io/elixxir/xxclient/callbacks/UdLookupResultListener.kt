package io.elixxir.xxclient.callbacks

import bindings.UdLookupCallback
import io.elixxir.xxclient.models.ContactAdapter
import io.elixxir.xxclient.utils.ContactData
import io.elixxir.xxclient.utils.nonNullResultOf
import io.elixxir.xxclient.utils.parse
import io.elixxir.xxclient.utils.parseArray
import java.lang.Exception

interface UdLookupResultListener {
    fun onResponse(response: Result<List<ContactData>>)
}

open class UdLookupCallbackAdapter(
    protected val listener: UdLookupResultListener
) : UdLookupCallback {
    override fun callback(contacts: ByteArray?, error: Exception?) {
        val contactsResult = parseArray(
            contacts,
            error,
            ByteArray::class.java
        )

        listener.onResponse(
            nonNullResultOf {
                contactsResult.getOrThrow()
            }
        )
    }
}