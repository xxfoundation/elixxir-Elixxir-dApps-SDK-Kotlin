package io.elixxir.xxclient.models

data class GroupReport(
    val id: ByteArray,
    val rounds: List<Long>,
    val roundUrl: String,
    val status: Long
) : BindingsModel