package io.elixxir.xxclient.models

data class GroupReport(
    val id: String,
    val rounds: List<Long>,
    val roundUrl: String,
    val status: Long
) : BindingsModel