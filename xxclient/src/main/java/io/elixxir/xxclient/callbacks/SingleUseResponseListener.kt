package io.elixxir.xxclient.callbacks

import bindings.SingleUseResponse
import io.elixxir.xxclient.models.SingleUseResponseReport
import io.elixxir.xxclient.utils.parseModel
import java.lang.Exception

interface SingleUseResponseListener {
    fun handle(report: Result<SingleUseResponseReport>)
}

open class SingleUseResponseAdapter(
    protected val listener: SingleUseResponseListener
) : SingleUseResponse {
    override fun callback(responseReport: ByteArray?, error: Exception?) {
        listener.handle(
            parseModel(responseReport, error)
        )
    }
}