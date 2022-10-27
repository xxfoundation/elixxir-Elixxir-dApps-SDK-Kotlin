package io.elixxir.xxclient.callbacks

import bindings.GroupChatProcessor
import io.elixxir.xxclient.models.BindingsModel.Companion.decode
import io.elixxir.xxclient.models.GroupChatMessage
import io.elixxir.xxclient.utils.ReceptionId
import io.elixxir.xxclient.utils.RoundId

interface GroupMessageListener {
    val name: String

    fun onMessageReceived(
        decryptedMessage: GroupChatMessage?,
        message: ByteArray?,
        receptionId: ReceptionId?,
        ephemeralId: Long,
        roundId: RoundId,
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
            decode<GroupChatMessage>(decryptedMessage),
            message,
            receptionId,
            ephemeralId,
            roundId,
            error
        )
    }

    override fun string(): String = listener.name
}