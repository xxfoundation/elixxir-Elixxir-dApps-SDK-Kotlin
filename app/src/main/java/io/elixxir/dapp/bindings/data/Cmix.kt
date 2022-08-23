package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.bindings.model.*
import io.elixxir.dapp.bindings.model.Contact
import io.elixxir.dapp.bindings.model.E2eId
import io.elixxir.dapp.bindings.model.NetworkFollowerStatus

internal interface Cmix {
    fun connect(
        e2eId: E2eId,
        recipientContact: Contact,
        e2eParams: E2eParams
    ): Connection

    fun startNetworkFollower(timeoutMs: Long)

    fun stopNetworkFollower()

    fun isNetworkHealthy(): Boolean

    fun registerHealthListener(listener: NetworkHealthListener): HealthListenerId

    fun unregisterHealthListener(id: HealthListenerId)

    fun getNodeRegistrationStatus(): NodeRegistrationStatus

    fun makeReceptionIdentity(): ReceptionIdentity

    fun getNetworkFollowerStatus(): NetworkFollowerStatus
}

