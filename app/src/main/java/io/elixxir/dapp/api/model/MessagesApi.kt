package io.elixxir.dapp.api.model

import io.elixxir.dapp.messaging.model.Message
import kotlinx.coroutines.flow.Flow

interface MessagesApi {
    val messages: Flow<Message>
    fun sendMessage()
    fun retryMessage()
}