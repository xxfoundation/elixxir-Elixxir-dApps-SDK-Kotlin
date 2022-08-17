package io.elixxir.dapp.bindings.data

import bindings.ReceptionIdentity
import io.elixxir.dapp.bindings.model.Connection
import io.elixxir.dapp.bindings.model.NetworkFollowerStatus
import io.elixxir.dapp.bindings.model.NodeRegistrationStatus
import bindings.Cmix as CoreCmix

@JvmInline
internal value class CmixAdapter(private val cmix: CoreCmix) : Cmix {

    override fun connect(): Connection {
        TODO("Not yet implemented")
    }

    override fun startNetworkFollower() {
        TODO("Not yet implemented")
    }

    override fun stopNetworkFollower() {
        TODO("Not yet implemented")
    }

    override fun isNetworkHealthy(): Boolean {
        TODO("Not yet implemented")
    }

    override fun registerHealthCallback() {
        TODO("Not yet implemented")
    }

    override fun unregisterHealthCallback() {
        TODO("Not yet implemented")
    }

    override fun getNodeRegistrationStatus(): NodeRegistrationStatus {
        TODO("Not yet implemented")
    }

    override fun makeReceptionIdentity(): ReceptionIdentity {
        TODO("Not yet implemented")
    }

    override fun getNetworkFollowerStatus(): NetworkFollowerStatus {
        TODO("Not yet implemented")
    }
}