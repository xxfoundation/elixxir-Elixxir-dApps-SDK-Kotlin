package io.elixxir.xxclient.models

data class BroadcastMessage(
    val roundId: Long,
    val ephemeralId: ByteArray,
    val payload: ByteArray
) : BindingsModel