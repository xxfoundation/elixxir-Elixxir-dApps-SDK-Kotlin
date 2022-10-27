package io.elixxir.xxclient.models

data class GroupSendReport(
    val rounds: List<Long>,
    val roundUrl: String,
    val timestamp: Long,
    val messageId: ByteArray,
) : BindingsModel