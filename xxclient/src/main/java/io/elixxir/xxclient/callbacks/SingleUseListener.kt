package io.elixxir.xxclient.callbacks

import bindings.SingleUseCallback
import io.elixxir.xxclient.models.SingleUseReport
import io.elixxir.xxclient.utils.parse
import java.lang.Exception

interface SingleUseListener {
    fun handle(report: Result<SingleUseReport>)
}

open class SingleUseAdapter(
    protected val listener: SingleUseListener
) : SingleUseCallback {
    override fun callback(report: ByteArray?, error: Exception?) {
        listener.handle(
            parse(report, error, SingleUseReport::class.java)
        )
    }
}