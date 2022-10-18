package io.elixxir.xxclient.e2e

import io.elixxir.xxclient.callbacks.AuthEventListener
import io.elixxir.xxclient.callbacks.E2eProcessor
import io.elixxir.xxclient.callbacks.MessageListener
import io.elixxir.xxclient.models.*
import io.elixxir.xxclient.utils.*

interface E2e {
    val id: E2eId
    val payloadSize: Long
    val receptionIdentity: ReceptionId
    val contact: Contact

    fun setPartitionSize(bytes: Long): Long

    fun getAllPartnerIds(): PartnersList
    fun getUdAddressFromNdf(): String
    fun getUdCertFromNdf(): ByteArray
    fun getUdContactFromNdf(): Contact
    fun addPartnerCallback(partnerId: UserId, callback: AuthEventListener)

    fun addService(processor: E2eProcessor)
    fun removeService(name: String)

    fun hasAuthenticatedChannel(partnerId: UserId): Boolean

    fun requestAuthenticatedChannel(
        contact: Contact,
        myFactsList: List<Fact>
    ): RoundId

    fun resetAuthenticatedChannel(contact: Contact): RoundId

    fun callAllReceivedRequests()
    fun getReceivedRequest(partnerId: UserId): Request
    fun deleteRequest(partnerId: UserId)
    fun deleteAllRequests()
    fun deleteReceivedRequests()
    fun deleteSentRequests()

    fun verifyOwnership(
        receivedContact: Contact,
        verifiedContact: Contact,
        e2eId: E2eId
    ): Boolean

    fun confirmReceivedRequest(contact: Contact): RoundId
    fun replayConfirmReceieveRequest(partnerId: UserId): RoundId


    fun sendE2e(
        messageType: MessageType,
        receiverId: UserId,
        payload: Payload,
        params: E2eParams
    ): SendReport

    fun registerListener(
        senderId: UserId,
        messageType: MessageType,
        e2eListener: MessageListener
    )
}