package io.elixxir.dapp.network.data

import bindings.Bindings
import bindings.Cmix
import io.elixxir.dapp.bindings.data.AuthCallbacksAdapter
import io.elixxir.dapp.bindings.data.CmixAdapter
import io.elixxir.dapp.bindings.data.E2e
import io.elixxir.dapp.bindings.data.E2eAdapter
import io.elixxir.dapp.bindings.model.E2eParams
import io.elixxir.dapp.bindings.model.ReceptionIdentity
import io.elixxir.dapp.api.model.CommonProperties
import io.elixxir.dapp.network.model.*
import io.elixxir.dapp.network.repository.NdfDataSource
import io.elixxir.dapp.session.model.*
import io.elixxir.dapp.session.repository.SessionKeyStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

internal interface NetworkManager {
    val connectionStatus: Flow<ConnectionStatus>
}

internal class DappNetworkManager private constructor(
    properties: CommonProperties,
    keyStore: SessionKeyStore,
    ndfSource: NdfDataSource
): NetworkManager, CommonProperties by properties {

    override val connectionStatus: Flow<ConnectionStatus> by ::_connectionStatus
    private val _connectionStatus: MutableStateFlow<ConnectionStatus> =
        MutableStateFlow(ConnectionStatus.DISCONNECTED)

    fun login(
        cmixId: Long,
        authCallbacks: AuthCallbacksAdapter,
        identity: ReceptionIdentity,
        e2eParamsJson: E2eParams
    ): E2e {
        return E2eAdapter(
            Bindings.login(cmixId, authCallbacks.value, identity.value, e2eParamsJson.value)
        )
    }

    fun newCmix(
        ndfJson: String,
        sessionFolderPath: String,
        sessionPassword: SessionPassword,
        registrationCode: String
    ) {
        Bindings.newCmix(
            ndfJson,
            sessionFolderPath,
            sessionPassword.value,
            registrationCode
        )
    }

    suspend fun loadCmix(
        sessionFolderPath: String,
        sessionPassword: SessionPassword,
        cmixParamsJson: E2eParams
    ): CmixAdapter = withContext(dispatcher) {
        CmixAdapter(
            Bindings.loadCmix(sessionFolderPath, sessionPassword.value, cmixParamsJson.value)
        )
    }

    fun createIdentity(): ReceptionIdentity {
        return ReceptionIdentity(
            Cmix().makeReceptionIdentity()
        )
    }

    fun initNetworkFollower() {

    }

    fun stopNetworkFollower() {

    }

    fun initUserDiscovery() {

    }

    companion object {
        internal fun newInstance(
            properties: CommonProperties,
            keyStore: SessionKeyStore,
            ndfSource: NdfDataSource
        ) = DappNetworkManager(properties, keyStore, ndfSource)
    }
}