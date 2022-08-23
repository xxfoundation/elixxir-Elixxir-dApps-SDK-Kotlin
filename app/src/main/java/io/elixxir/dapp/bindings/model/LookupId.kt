package io.elixxir.dapp.bindings.model

@JvmInline
value class LookupId(val value: ByteArray) {

    companion object {
        val placeholder: ByteArray = byteArrayOf()
    }
}