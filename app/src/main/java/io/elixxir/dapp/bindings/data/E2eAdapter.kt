package io.elixxir.dapp.bindings.data

import bindings.E2e as CoreE2e
import io.elixxir.dapp.bindings.model.ReceptionIdentity

@JvmInline
internal value class E2eAdapter(private val e2e: CoreE2e) : E2e {

    fun confirm(userId: ByteArray) {
        e2e.confirm(userId)
    }

    override fun deleteAllRequests() {
        e2e.deleteAllRequests()
    }

    override fun deleteRequest() {
        TODO("Not yet implemented")
    }

    override fun deleteSendRequests() {
        TODO("Not yet implemented")
    }

    override fun getAllPartnerIds() {
        TODO("Not yet implemented")
    }

    override fun hasAuthenticatedChannel() {
        TODO("Not yet implemented")
    }

    override fun registerListener() {
        TODO("Not yet implemented")
    }

    override fun reset() {
        TODO("Not yet implemented")
    }

    override fun sendRequest() {
        TODO("Not yet implemented")
    }

    override fun verifyOwnership() {
        TODO("Not yet implemented")
    }

    override fun sendE2e() {
        TODO("Not yet implemented")
    }

    fun deleteReceivedRequests() {
        e2e.deleteReceiveRequests()
    }

    override val payloadSize: Long
        get() = TODO("Not yet implemented")

    override fun getReceptionId(): ReceptionIdentity {
        return ReceptionIdentity(e2e.receptionID)
    }

    override fun getContact() {
        TODO("Not yet implemented")
    }

    override fun callAllReceivedRequests() {
        TODO("Not yet implemented")
    }

    override fun confirmRequest() {
        TODO("Not yet implemented")
    }

}