package io.elixxir.xxclient.bindings

import io.elixxir.xxclient.backup.Backup
import io.elixxir.xxclient.callbacks.*
import io.elixxir.xxclient.channel.Channel
import io.elixxir.xxclient.cmix.CMix
import io.elixxir.xxclient.dummytraffic.DummyTraffic
import io.elixxir.xxclient.e2e.E2e
import io.elixxir.xxclient.filetransfer.FileTransfer
import io.elixxir.xxclient.models.*
import io.elixxir.xxclient.models.BackupUpdateListener
import io.elixxir.xxclient.userdiscovery.UserDiscovery
import io.elixxir.xxclient.utils.*

interface Bindings {
    val defaultCmixParams: CmixParams
    val defaultE2eParams: E2eParams
    val defaultE2eFileTransferParams: E2eFileTransferParams
    val defaultFileTransferParams: FileTransferParams
    val defaultSingleUseParams: SingleUseParams
    val gitVersion: String
    val dependencies: String

    fun downloadAndVerifySignedNdf(
        environmentUrl: String,
        certificate: Certificate,
    ): Ndf

    fun generateSecret(byteLength: Long): Password

    fun loadCmix(
        sessionFileDirectory: String,
        sessionPassword: Password,
        cmixParams: CmixParams
    ): CMix

    fun login(
        e2eId: E2eId,
        authCallbacks: AuthEventListener,
        receptionIdentity: ReceptionIdentity,
        e2eParams: E2eParams
    ): E2e

    fun loginEphemeral(
        e2eId: E2eId,
        authCallbacks: AuthEventListener,
        receptionIdentity: ReceptionIdentity,
        e2eParams: E2eParams
    ): E2e

    fun getOrCreateUd(
        e2eId: E2eId,
        networkFollowerStatus: NetworkFollowerStatus,
        username: String,
        signature: Signature,
        udCert: CertificateData,
        contact: Contact,
        udIpAddress: String
    ): UserDiscovery

    fun newUdFromBackup(
        e2eId: E2eId,
        networkFollowerStatus: NetworkFollowerStatus,
        emailFact: Fact,
        phoneFact: Fact,
        udCert: CertificateData,
        contact: Contact,
        udAddress: String
    ): UserDiscovery

    fun newDummyTrafficManager(
        cmixId: Long,
        maxNumMessages: Long,
        avgSendDeltaMS: Long,
        randomRangeMS: Long
    ): DummyTraffic

    fun registerLogger(logLevel: LogLevel, logWriter: LogWriter)

    fun initializeBackup(
        e2eId: E2eId,
        udId: Long,
        backupPassword: String,
        updateListener: BackupUpdateListener
    ): Backup

    fun resumeBackup(
        e2eId: E2eId,
        udId: Long,
        updateListener: BackupUpdateListener
    ): Backup

    fun fetchSignedNdf(
        url: String,
        cert: String
    ): Ndf

    fun getReceptionIdentity(
        key: String,
        e2eId: E2eId
    ): ReceptionIdentity

    fun newFileTransferManager(
        e2eId: E2eId,
        incomingFileListener: IncomingFileListener,
        e2eFileTransferParamsJson: ByteArray,
        fileTransferParamsJson: ByteArray
    ): FileTransfer

    fun getIdFromContact(contactData: ContactData): ByteArray
    fun getPublicKeyFromContact(contactData: ContactData): ByteArray
    fun getFactsFromContact(contactData: ContactData): Fact
    fun setFactsOnContact(contactData: ContactData, fact: Fact): ContactData

    fun searchUd(
        e2eId: E2eId,
        udContact: Contact,
        listener: UdSearchResultListener,
        factsListJson: ByteArray,
        singleRequestParamsJson: ByteArray
    ): ContactList

    fun lookupUd(
        e2eId: E2eId,
        udContact: Contact,
        listener: UdLookupResultListener,
        lookupId: UserId,
        singleRequestParamsJson: ByteArray
    ): SingleUseReport

    fun newBroadcastChannel(cmixId: Long, channelDef: ChannelDef): Channel

    fun storeReceptionIdentity(
        key: String,
        identity: ReceptionIdentity,
        cmixId: Long
    )

    fun transmitSingleUse(
        e2eId: E2eId,
        recipient: Contact,
        tag: String,
        payload: Payload,
        paramsJson: ByteArray,
        listener: SingleUseResponseListener
    ): SingleUseReport

    fun updateCommonErrors(errorsJson: String)
}