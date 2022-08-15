package io.elixxir.dapp.bindings.data

import bindings.E2e as CoreE2e
import io.elixxir.dapp.bindings.model.ReceptionIdentity

@JvmInline
internal value class E2eAdapter(private val e2e: CoreE2e) : E2e {

    fun confirm(userId: ByteArray) {
        e2e.confirm(userId)
    }

    fun deleteAllRequests() {
        e2e.deleteAllRequests()
    }

    fun deleteReceivedRequests() {
        e2e.deleteReceiveRequests()
    }

    fun getReceptionId(): ReceptionIdentity {
        return ReceptionIdentity(e2e.receptionID)
    }

}