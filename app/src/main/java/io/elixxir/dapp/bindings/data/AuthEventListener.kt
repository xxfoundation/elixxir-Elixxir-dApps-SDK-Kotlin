package io.elixxir.dapp.bindings.data

import bindings.AuthCallbacks

interface AuthEventListener {
    fun onConfirm(var1: ByteArray?, var2: ByteArray?, var3: Long, var5: Long)

    fun onRequest(var1: ByteArray?, var2: ByteArray?, var3: Long, var5: Long)

    fun onReset(var1: ByteArray?, var2: ByteArray?, var3: Long, var5: Long)
}

@JvmInline
internal value class AuthCallbacksAdapter(val value: AuthCallbacks)