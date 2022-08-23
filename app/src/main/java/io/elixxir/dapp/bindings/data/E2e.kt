package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.bindings.model.*
import io.elixxir.dapp.bindings.model.E2eParams
import io.elixxir.dapp.bindings.model.ReceptionIdentity
import io.elixxir.dapp.user.model.UserId

internal interface E2e {
    val payloadSize: Long

    fun getReceptionId(): ReceptionIdentity
    fun getContact(): Contact
    fun callAllReceivedRequests()
    fun confirmRequest(userId: UserId)
    fun deleteAllRequests()
    fun deleteRequest(request: Request)
    fun deleteSentRequests()

    fun getAllPartnerIds(): PartnersList

    fun hasAuthenticatedChannel(contact: Contact): Boolean

    fun registerListener(
        senderId: UserId,
        messageType: MessageType,
        e2eListener: E2eListener
    )

    fun reset(contact: Contact): RoundId

    fun sendRequest(
        contact: Contact,
        myFactsList: FactsList
    ): RoundId

    fun verifyOwnership(
        receivedUserId: UserId,
        verifiedContact: Contact,
        e2eHandler: E2eHandler
    ): Boolean

    fun sendE2e(
        messageType: MessageType,
        receiverId: UserId,
        payload: Payload,
        params: E2eParams
    ): SendReport
}

