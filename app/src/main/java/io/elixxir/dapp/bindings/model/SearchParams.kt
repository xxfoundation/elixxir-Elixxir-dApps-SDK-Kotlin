package io.elixxir.dapp.bindings.model

@JvmInline
internal value class SearchParams(val value: ByteArray) {

    companion object {
        val placeholder: ByteArray = byteArrayOf()
    }
}