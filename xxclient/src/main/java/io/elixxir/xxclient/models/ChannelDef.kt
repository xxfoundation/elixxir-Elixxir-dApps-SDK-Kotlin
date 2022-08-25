package io.elixxir.xxclient.models

data class ChannelDef(
    val name: String,
    val description: String,
    val salt: ByteArray,
    val pubKey: ByteArray
) : BindingsModel