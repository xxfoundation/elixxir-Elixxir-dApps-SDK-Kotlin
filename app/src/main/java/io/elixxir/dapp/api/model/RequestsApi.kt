package io.elixxir.dapp.api.model

import io.elixxir.dapp.request.model.IncomingRequest
import io.elixxir.dapp.request.model.Request
import io.elixxir.dapp.request.model.OutgoingRequest
import kotlinx.coroutines.flow.Flow

interface RequestsApi {
    val requests: Flow<Request>
    fun sendRequest(request: OutgoingRequest)
    fun acceptRequest(request: IncomingRequest)
    fun retryRequest(request: Request)
}