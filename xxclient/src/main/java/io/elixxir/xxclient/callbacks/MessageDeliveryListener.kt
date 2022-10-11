package io.elixxir.xxclient.callbacks

import bindings.MessageDeliveryCallback
import io.elixxir.xxclient.models.BindingsModel.Companion.decode
import io.elixxir.xxclient.models.RoundResults

interface MessageDeliveryListener {
    fun onMessageSent(deliveryReport: Result<RoundResults>)
}

open class MessageDeliveryCallbackAdapter(
    protected val listener: MessageDeliveryListener
) : MessageDeliveryCallback {

    override fun eventCallback(delivered: Boolean, timedOut: Boolean, roundResults: ByteArray?) {
        when {
            delivered -> roundResults?.let { onSuccess(it) }
            timedOut -> onTimeOut()
            else -> onFailed()
        }
    }

    private fun onSuccess(roundResults: ByteArray) {
        listener.onMessageSent(
            Result.success(
                decode(roundResults)
            )
        )
    }

    private fun onTimeOut() {
        listener.onMessageSent(
            Result.failure(DeliveryTimeOutException())
        )
    }

    private fun onFailed() {
        listener.onMessageSent(
            Result.failure(DeliveryFailedException())
        )
    }
}

sealed class MessageDeliveryException : Exception()
class DeliveryFailedException : MessageDeliveryException()
class DeliveryTimeOutException : MessageDeliveryException()

