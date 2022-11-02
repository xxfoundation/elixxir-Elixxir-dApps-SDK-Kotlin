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
        Log.d("UdLookup", "contacts: ${contacts?.decodeToString() ?: "null"}")
        val contactsResult = parseData(
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