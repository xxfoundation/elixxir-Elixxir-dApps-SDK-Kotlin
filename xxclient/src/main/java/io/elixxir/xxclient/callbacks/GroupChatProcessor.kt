package io.elixxir.xxclient.callbacks

import bindings.GroupChatProcessor as GroupChatBindings

interface GroupChatProcessor {
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
    protected val groupChatProcessor: GroupChatProcessor
): GroupChatBindings, GroupChatProcessor by groupChatProcessor {
    override fun process(
        decryptedMessage: ByteArray?,
        message: ByteArray?,
        receptionId: ByteArray?,
        ephemeralId: Long,
        roundId: Long,
        error: Exception?
    ) {
        groupChatProcessor.onMessageReceived(
            decryptedMessage, message, receptionId, ephemeralId, roundId, error
        )
    }

    override fun string(): String = groupChatProcessor.name
}