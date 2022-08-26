package io.elixxir.xxclient.models

data class GroupReport(
    val id: ByteArray,
    val roundIds: List<Long>,
    val status: Long
) : BindingsModel