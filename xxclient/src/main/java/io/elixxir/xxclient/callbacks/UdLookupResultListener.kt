package io.elixxir.xxclient.callbacks

import android.util.Log
import bindings.UdLookupCallback
import io.elixxir.xxclient.utils.*
import java.lang.Exception

interface UdLookupResultListener {
    fun onResponse(response: Result<ContactData>)
}

open class UdLookupCallbackAdapter(
    protected val listener: UdLookupResultListener
) : UdLookupCallback {
    override fun callback(contacts: ByteArray?, error: Exception?) {
        val contactsResult = parseContactData(
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