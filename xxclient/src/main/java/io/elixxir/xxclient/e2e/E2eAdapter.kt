package io.elixxir.xxclient.e2e

import io.elixxir.xxclient.callbacks.*
import io.elixxir.xxclient.models.*
import io.elixxir.xxclient.models.BindingsModel.Companion.decode
import io.elixxir.xxclient.models.BindingsModel.Companion.encode
import io.elixxir.xxclient.utils.*
import bindings.E2e as E2eBindings

open class E2eAdapter(protected val e2e: E2eBindings) : E2e{
    override val id: Long
        get() = e2e.id
    override val payloadSize: Long
        get() = e2e.payloadSize()
    override val receptionIdentity: ReceptionId
        get() = e2e.receptionID
    override val contact: Contact
        get() = ContactAdapter(e2e.contact)

    override fun setPartitionSize(bytes: Long): Long {
        return e2e.partitionSize(bytes)
    }

    override fun getAllPartnerIds(): PartnersList {
        return decode(e2e.allPartnerIDs, PartnersList::class.java)
    }

    override fun getUdAddressFromNdf(): String {
        return e2e.udAddressFromNdf
    }

    override fun getUdCertFromNdf(): ByteArray {
        return e2e.udCertFromNdf
    }

    override fun getUdContactFromNdf(): Contact {
        return ContactAdapter(e2e.udContactFromNdf)
    }

    override fun addPartnerCallback(partnerId: UserId, callback: AuthEventListener) {
        e2e.addPartnerCallback(
            partnerId,
            AuthCallbacksAdapter(callback)
        )
    }

    override fun addService(processor: E2eProcessor) {
        e2e.addService(
            processor.name,
            ProcessorAdapter(processor)
        )
    }

    override fun removeService(name: String) {
        e2e.removeService(name)
    }

    override fun hasAuthenticatedChannel(partnerId: UserId): Boolean {
        return e2e.hasAuthenticatedChannel(partnerId)
    }

    override fun requestAuthenticatedChannel(contact: Contact, myFactsList: FactsList): RoundId {
        return e2e.request(
            contact.encoded(),
            encode(myFactsList)
        )
    }

    override fun resetAuthenticatedChannel(contact: Contact): RoundId {
        return e2e.reset(contact.encoded())
    }

    override fun callAllReceivedRequests() {
        e2e.callAllReceivedRequests()
    }

    override fun getReceivedRequest(partnerId: UserId): Request {
        return decode(
            e2e.getReceivedRequest(partnerId),
            Request::class.java
        )
    }

    override fun deleteRequest(partnerId: UserId) {
        e2e.deleteRequest(partnerId)
    }

    override fun deleteAllRequests() {
        e2e.deleteAllRequests()
    }

    override fun deleteReceivedRequests() {
        e2e.deleteReceiveRequests()
    }

    override fun deleteSentRequests() {
        e2e.deleteSentRequests()
    }

    override fun verifyOwnership(
        receivedContact: Contact,
        verifiedContact: Contact,
        e2eHandler: E2eId
    ): Boolean {
        return e2e.verifyOwnership(
            receivedContact.encoded(),
            verifiedContact.encoded(),
            e2eHandler
        )
    }

    override fun confirmReceivedRequest(contact: Contact): RoundId {
        return e2e.confirm(contact.encoded())
    }

    override fun replayConfirmReceieveRequest(partnerId: UserId): RoundId {
        return e2e.replayConfirm(partnerId)
    }

    override fun sendE2e(
        messageType: MessageType,
        receiverId: UserId,
        payload: Payload,
        params: E2eParams
    ): SendReport {
        val reportData = e2e.sendE2E(
            messageType.code,
            receiverId,
            payload,
            params
        )
        return decode(reportData, SendReport::class.java)
    }

    override fun registerListener(
        senderId: UserId,
        messageType: MessageType,
        e2eListener: MessageListener
    ) {
        e2e.registerListener(
            senderId,
            messageType.code,
            MessageListenerAdapter(e2eListener)
        )
    }
}