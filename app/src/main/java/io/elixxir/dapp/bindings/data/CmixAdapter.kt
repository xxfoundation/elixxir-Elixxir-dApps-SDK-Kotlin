package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.bindings.model.*
import io.elixxir.dapp.bindings.model.Connection
import io.elixxir.dapp.bindings.model.Contact
import io.elixxir.dapp.bindings.model.E2eId
import io.elixxir.dapp.bindings.model.E2eParams
import io.elixxir.dapp.bindings.model.NetworkFollowerStatus
import bindings.Cmix as CoreCmix

@JvmInline
internal value class CmixAdapter(private val cmix: CoreCmix) : Cmix {

    override fun connect(
        e2eId: E2eId,
        recipientContact: Contact,
        e2eParams: E2eParams
    ): Connection {
        // TODO: Use factory method to get Connection implementation
        return ConnectionAdapter(
            cmix.connect(
                e2eId.value,
                recipientContact.value,
                e2eParams.value
            )
        )
    }

    override fun startNetworkFollower(timeoutMs: Long) {
        cmix.startNetworkFollower(timeoutMs)
    }

    override fun stopNetworkFollower() {
        cmix.stopNetworkFollower()
    }

    override fun isNetworkHealthy(): Boolean {
        return cmix.isHealthy
    }

    override fun registerHealthListener(listener: NetworkHealthListener): HealthListenerId {
        return HealthListenerId(
            cmix.addHealthCallback(HealthCallbackAdapter.placeholder)
        )
    }

    override fun unregisterHealthListener(id: HealthListenerId) {
        cmix.removeHealthCallback(id.value)
    }

    override fun getNodeRegistrationStatus(): NodeRegistrationStatus {
        return NodeRegistrationStatus(cmix.nodeRegistrationStatus)
    }

    override fun makeReceptionIdentity(): ReceptionIdentity {
        return ReceptionIdentity(cmix.makeReceptionIdentity())
    }

    override fun getNetworkFollowerStatus(): NetworkFollowerStatus {
        return NetworkFollowerStatus.from(cmix.networkFollowerStatus())
    }
}