package io.elixxir.xxclient.models

data class GroupSendReport(
    val roundId: Long,
    val timestamp: Long,
    val messageId: ByteArray
)