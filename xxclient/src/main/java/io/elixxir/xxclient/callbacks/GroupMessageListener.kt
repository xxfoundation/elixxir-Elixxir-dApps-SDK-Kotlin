package io.elixxir.xxclient.callbacks

import bindings.GroupChatProcessor

interface GroupMessageListener {
    val name: String

    fun onMessageReceived(
        decryptedMessage: ByteArray?,
        message: ByteArray?,
        receptionId: ByteArray?,
        ephemeralId: Long,
        roundId: Long,
        error: Exception?
    )
}

open class GroupChatProcessorAdapter(
    protected val listener: GroupMessageListener
): GroupChatProcessor, GroupMessageListener by listener {
    override fun process(
        decryptedMessage: ByteArray?,
        message: ByteArray?,
        receptionId: ByteArray?,
        ephemeralId: Long,
        roundId: Long,
        error: Exception?
    ) {
        listener.onMessageReceived(
            decryptedMessage, message, receptionId, ephemeralId, roundId, error
        )
    }

    override fun string(): String = listener.name
}