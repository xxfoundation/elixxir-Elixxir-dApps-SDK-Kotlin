package io.elixxir.xxclient.models

import io.elixxir.xxclient.utils.Payload
import io.elixxir.xxclient.utils.UserId

data class GroupChatMessage(
    val groupId: ByteArray,
    val senderId: UserId,
    val messageId: ByteArray,
    val payload: Payload,
    val timestamp: Long
) : BindingsModel