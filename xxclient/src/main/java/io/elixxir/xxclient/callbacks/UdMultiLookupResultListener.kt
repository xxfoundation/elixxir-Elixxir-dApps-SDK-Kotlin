package io.elixxir.xxclient.callbacks

import bindings.UdMultiLookupCallback
import io.elixxir.xxclient.models.BindingsList
import io.elixxir.xxclient.models.BindingsListAdapter
import io.elixxir.xxclient.models.BindingsModel.Companion.decode
import io.elixxir.xxclient.models.BindingsModel.Companion.encode
import io.elixxir.xxclient.models.Contact
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

    override fun callback(contacts: ByteArray?, failedIds: ByteArray?, error: Exception?) {
        TODO()
//        listener.onResponse(
//            parse(
//                encode(
//                    UdMultiLookupResult(
//                    decode(contacts, BindingsListAdapter::class.java),
//                    decode(failedIds, BindingsListAdapter<UserId>)
//                    )
//                ),
//                error,
//                UdMultiLookupResult::class.java
//            )
//        )
    }

}