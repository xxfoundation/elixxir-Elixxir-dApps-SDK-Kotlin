package io.elixxir.xxclient.callbacks

import bindings.AuthCallbacks as BindingsAuthCallbacks

interface AuthCallbacks {
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
    protected val authCallbacks: AuthCallbacks
) : AuthCallbacks by authCallbacks, BindingsAuthCallbacks {
    override fun confirm(p0: ByteArray?, p1: ByteArray?, p2: Long, p3: Long) {
        authCallbacks.onConfirm(p0, p1, p2, p3)
    }

    override fun request(p0: ByteArray?, p1: ByteArray?, p2: Long, p3: Long) {
        authCallbacks.onRequest(p0, p1, p2, p3)
    }

    override fun reset(p0: ByteArray?, p1: ByteArray?, p2: Long, p3: Long) {
        authCallbacks.onReset(p0, p1, p2, p3)
    }
}