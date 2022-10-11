package io.elixxir.xxclient.callbacks

import android.util.Log
import bindings.UdMultiLookupCallback
import io.elixxir.xxclient.models.BindingsListAdapter
import io.elixxir.xxclient.models.BindingsModel.Companion.decode
import io.elixxir.xxclient.models.BindingsModel.Companion.encode
import io.elixxir.xxclient.models.UdMultiLookupResult
import io.elixxir.xxclient.utils.UserId
import io.elixxir.xxclient.utils.parse
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
        Log.d("array of ContactData", contacts?.decodeToString() ?: "")
        Log.d("failed ids string array", failedIds?.decodeToString() ?: "")

        listener.onResponse(
            parse(
                encode(
                    UdMultiLookupResult(
                        decode(contacts, BindingsListAdapter::class.java),
                        decode(failedIds, BindingsListAdapter<UserId>)
                    )
                ),
                error,
                UdMultiLookupResult::class.java
            )
        )
    }

}