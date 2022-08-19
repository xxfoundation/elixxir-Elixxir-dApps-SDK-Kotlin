package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.bindings.model.*
import io.elixxir.dapp.bindings.model.E2eParams
import io.elixxir.dapp.bindings.model.ReceptionIdentity
import bindings.E2e as CoreE2e
import io.elixxir.dapp.user.model.UserId

@JvmInline
internal value class E2eAdapter(private val e2e: CoreE2e) : E2e {
    override val payloadSize: Long
        get() = e2e.payloadSize()

    override fun getReceptionId(): ReceptionIdentity {
        return ReceptionIdentity(e2e.receptionID)
    }

    override fun getContact(): Contact {
        return Contact(e2e.contact)
    }

    override fun callAllReceivedRequests() {
        e2e.callAllReceivedRequests()
    }

    override fun confirmRequest(userId: UserId) {
        e2e.confirm(userId.value)
    }

    override fun deleteAllRequests() {
        e2e.deleteAllRequests()
    }

    override fun deleteRequest(request: Request) {
        e2e.deleteRequest(request.value)
    }

    override fun deleteSentRequests() {
        e2e.deleteSentRequests()
    }

    override fun getAllPartnerIds(): PartnersList {
        return PartnersList(e2e.allPartnerIDs)
    }

    override fun hasAuthenticatedChannel(contact: Contact): Boolean {
        return e2e.hasAuthenticatedChannel(contact.value)
    }

    override fun registerListener(
        senderId: UserId,
        messageType: MessageType,
        e2eListener: E2eListener
    ) {
        e2e.registerListener(
            senderId.value,
            messageType.code,
            e2eListener
        )
    }

    override fun reset(contact: Contact): RoundId {
        return RoundId(
            e2e.reset(contact.value)
        )
    }

    override fun sendRequest(
        contact: Contact,
        myFactsList: FactsList
    ): RoundId {
        return RoundId(
            e2e.request(contact.value, myFactsList.value)
        )
    }

    override fun verifyOwnership(
        receivedUserId: UserId,
        verifiedContact: Contact,
        e2eHandler: E2eHandler
    ): Boolean {
        return e2e.verifyOwnership(
            receivedUserId.value,
            verifiedContact.value,
            e2eHandler.value
        )
    }

    override fun sendE2e(
        messageType: MessageType,
        receiverId: UserId,
        payload: Payload,
        params: E2eParams
    ): SendReport {
        return SendReport(
            e2e.sendE2E(
                messageType.code,
                receiverId.value,
                payload.value,
                params.value
            )
        )
    }
}