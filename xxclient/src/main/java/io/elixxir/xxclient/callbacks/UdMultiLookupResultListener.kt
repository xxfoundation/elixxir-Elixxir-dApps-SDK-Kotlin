package io.elixxir.xxclient.callbacks

import bindings.UdMultiLookupCallback
import io.elixxir.xxclient.models.ContactAdapter
import io.elixxir.xxclient.models.UdMultiLookupResult
import io.elixxir.xxclient.utils.nonNullResultOf
import io.elixxir.xxclient.utils.parseDataArray
import java.lang.Exception

interface UdMultiLookupResultListener {
    fun onResponse(response: Result<UdMultiLookupResult>)
}

open class UdMultiLookupCallbackAdapter(
    protected val listener: UdMultiLookupResultListener
) : UdMultiLookupCallback {

    override fun callback(
        contacts: ByteArray? /* list of contacts */,
        failedIds: ByteArray?, /* list of ids */
        error: Exception?
    ) {
        val contactsResult = parseDataArray(
            contacts,
            error
        )
        val failedLookupsResult = parseDataArray(
            failedIds,
            error
        )

        listener.onResponse(
            nonNullResultOf {
                UdMultiLookupResult(
                    contactsResult.getOrThrow().map {
                        ContactAdapter(it)
                    },
                    failedLookupsResult.getOrThrow(),
                )
            }
        )
    }

}