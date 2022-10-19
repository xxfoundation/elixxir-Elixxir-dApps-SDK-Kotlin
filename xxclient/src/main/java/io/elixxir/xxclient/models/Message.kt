package io.elixxir.xxclient.models

import io.elixxir.xxclient.utils.Payload

data class Message(
    val messageType: Long,
    val id: ByteArray,
    val payload: Payload,
    val sender: ByteArray,
    val recipientId: ByteArray,
    val ephemeralId: Long,
    val timestamp: Long,
    val encrypted: Boolean,
    val roundId: Long,
    val roundUrl: String?
) : BindingsModel