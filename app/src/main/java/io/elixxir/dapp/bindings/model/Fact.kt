package io.elixxir.dapp.bindings.model

@JvmInline
internal value class Fact(val value: ByteArray) {

    companion object {
        val placeholder: ByteArray = Fact(byteArrayOf()).value
    }
}