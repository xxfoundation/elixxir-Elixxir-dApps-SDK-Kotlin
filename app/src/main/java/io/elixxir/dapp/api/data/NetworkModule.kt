package io.elixxir.dapp.api.data

import io.elixxir.dapp.api.model.NetworkApi
import io.elixxir.dapp.network.model.ConnectionStatus
import io.elixxir.dapp.network.data.NetworkManager
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

internal class NetworkModule(
    private val networkManager: NetworkManager
) : NetworkApi {
    private val scope: CoroutineScope = CoroutineScope(
        CoroutineName("AccountModule")
                + Job()
                + Dispatchers.Default
    )

    override val connectionStatus: Flow<ConnectionStatus> by networkManager::connectionStatus

    override fun connect() {
        TODO("Not yet implemented")
    }

    override fun disconnect() {
        TODO("Not yet implemented")
    }
}