package io.elixxir.xxclient.callbacks

import bindings.AuthCallbacks

interface AuthEventListener {
    fun onConfirm(
        contact: ByteArray?,
        receptionId: ByteArray?,
        ephemeralId: Long,
        roundId: Long
    )

    fun onRequest(
        contact: ByteArray?,
        receptionId: ByteArray?,
        ephemeralId: Long,
        roundId: Long
    )

    fun onReset(
        contact: ByteArray?,
        receptionId: ByteArray?,
        ephemeralId: Long,
        roundId: Long
    )
}

open class AuthCallbacksAdapter(
    protected val listener: AuthEventListener
) : AuthEventListener by listener, AuthCallbacks {
    override fun confirm(
        contact: ByteArray?,
         receptionId: ByteArray?,
         ephemeralId: Long,
         roundId: Long
    ) {
        listener.onConfirm(contact, receptionId, ephemeralId, roundId)
    }

    override fun request(
        contact: ByteArray?,
        receptionId: ByteArray?,
        ephemeralId: Long,
        roundId: Long
    ) {
        listener.onRequest(contact, receptionId, ephemeralId, roundId)
    }

    override fun reset(
        contact: ByteArray?,
        receptionId: ByteArray?,
        ephemeralId: Long,
        roundId: Long
    ) {
        listener.onReset(contact, receptionId, ephemeralId, roundId)
    }
}