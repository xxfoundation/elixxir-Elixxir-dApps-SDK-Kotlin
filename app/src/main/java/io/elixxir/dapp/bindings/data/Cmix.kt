package io.elixxir.dapp.bindings.data

import bindings.ReceptionIdentity
import io.elixxir.dapp.bindings.model.Connection
import io.elixxir.dapp.bindings.model.NetworkFollowerStatus
import io.elixxir.dapp.bindings.model.NodeRegistrationStatus

internal interface Cmix {
    fun connect(): Connection
    fun startNetworkFollower()
    fun stopNetworkFollower()
    fun isNetworkHealthy(): Boolean
    fun registerHealthCallback()
    fun unregisterHealthCallback()
    fun getNodeRegistrationStatus(): NodeRegistrationStatus
    fun makeReceptionIdentity(): ReceptionIdentity
    fun getNetworkFollowerStatus(): NetworkFollowerStatus
}

