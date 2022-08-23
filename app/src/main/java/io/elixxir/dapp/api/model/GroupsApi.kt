package io.elixxir.dapp.api.model

import io.elixxir.dapp.group.model.Group
import io.elixxir.dapp.messaging.model.Message
import io.elixxir.dapp.user.model.User
import kotlinx.coroutines.flow.Flow

interface GroupsApi {
    val groupMessages: Flow<Message>
    fun sendInvitation(group: Group)
    fun resendInvitation(member: User)
    fun joinGroup(group: Group)
    fun leaveGroup(group: Group)
    fun sendMessage(message: Message, group: Group)
    fun retryMessage(message: Message, group: Group)
}