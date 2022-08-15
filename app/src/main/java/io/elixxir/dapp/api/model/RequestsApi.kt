package io.elixxir.dapp.api.model

import io.elixxir.dapp.request.model.Request
import kotlinx.coroutines.flow.Flow

interface RequestsApi {
    val requests: Flow<Request>
    fun sendRequest()
    fun acceptRequest()
    fun retryRequest()
}