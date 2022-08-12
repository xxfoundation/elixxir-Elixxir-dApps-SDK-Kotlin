package io.elixxir.dapp.network.data

import bindings.Bindings
import bindings.Cmix
import io.elixxir.dapp.model.CommonProperties
import io.elixxir.dapp.network.model.*
import io.elixxir.dapp.session.model.*
import kotlinx.coroutines.withContext

internal interface NetworkManager {

}

internal class DappNetworkManager private constructor(
    properties: CommonProperties
): NetworkManager, CommonProperties by properties {

    private fun login(
        cmixId: Long,
        authCallbacks: AuthCallbacksMediator,
        identity: ReceptionIdentity,
        e2eParamsJson: E2eParams
    ): E2eMediator {
        return E2eMediator(
            Bindings.login(cmixId, authCallbacks.value, identity.value, e2eParamsJson.value)
        )
    }

    private fun newCmix(
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

    private suspend fun loadCmix(
        sessionFolderPath: String,
        sessionPassword: SessionPassword,
        cmixParamsJson: E2eParams
    ): CmixMediator = withContext(dispatcher) {
        CmixMediator(
            Bindings.loadCmix(sessionFolderPath, sessionPassword.value, cmixParamsJson.value)
        )
    }

    private fun createIdentity(): ReceptionIdentity {
        return ReceptionIdentity(
            Cmix().makeReceptionIdentity()
        )
    }

    private fun initNetworkFollower() {

    }

    private fun stopNetworkFollower() {

    }

    private fun initUserDiscovery() {

    }

    companion object {
        internal fun newInstance(properties: CommonProperties) = DappNetworkManager(properties)
    }
}