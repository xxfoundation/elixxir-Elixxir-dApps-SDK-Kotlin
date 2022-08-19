package io.elixxir.dapp.api.model

import io.elixxir.dapp.messaging.model.Message
import io.elixxir.dapp.user.model.User
import kotlinx.coroutines.flow.Flow

interface MessagesApi {
    val messages: Flow<Message>
    fun sendMessage(message: Message, recipient: User)
    fun retryMessage(message: Message, recipient: User)
}