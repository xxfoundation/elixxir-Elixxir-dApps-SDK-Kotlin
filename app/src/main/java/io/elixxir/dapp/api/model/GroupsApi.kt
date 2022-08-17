package io.elixxir.dapp.api.model

import io.elixxir.dapp.messaging.model.Message
import kotlinx.coroutines.flow.Flow

interface GroupsApi {
    val groupMessages: Flow<Message>
    fun sendInvitation()
    fun resendInvitation()
    fun joinGroup()
    fun leaveGroup()
    fun sendMessage()
    fun retryMessage()
}