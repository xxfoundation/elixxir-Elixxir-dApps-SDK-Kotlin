package io.elixxir.xxclient.models

data class SingleUseResponseReport(
    val roundIds: List<Long>,
    val payload: ByteArray,
    val ephemeralId: Long,
    val receptionId: ByteArray,
    val error: String?
) : BindingsModel