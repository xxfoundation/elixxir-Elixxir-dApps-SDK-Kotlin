package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.bindings.model.ReceptionIdentity

internal interface E2e {
    val payloadSize: Long

    fun getReceptionId(): ReceptionIdentity
    fun getContact()
    fun callAllReceivedRequests()
    fun confirmRequest()
    fun deleteAllRequests()
    fun deleteRequest()
    fun deleteSendRequests()
    fun getAllPartnerIds()
    fun hasAuthenticatedChannel()
    fun registerListener()
    fun reset()
    fun sendRequest()
    fun verifyOwnership()
    fun sendE2e()
}

