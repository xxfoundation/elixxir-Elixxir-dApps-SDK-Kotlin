package io.elixxir.xxclient.models

data class SingleUseReport(
    val roundIds: List<Long>,
    val payload: ByteArray,
    val partner: ByteArray,
    val ephemeralId: Long,
    val receptionId: ByteArray
) : BindingsModel