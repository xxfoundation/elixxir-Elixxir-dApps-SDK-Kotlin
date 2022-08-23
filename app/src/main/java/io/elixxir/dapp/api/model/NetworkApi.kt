package io.elixxir.dapp.api.model

import io.elixxir.dapp.network.model.ConnectionStatus
import kotlinx.coroutines.flow.Flow

interface NetworkApi {
    val connectionStatus: Flow<ConnectionStatus>
    fun connect(): Result<ConnectionStatus>
    fun disconnect(): Result<ConnectionStatus>
}