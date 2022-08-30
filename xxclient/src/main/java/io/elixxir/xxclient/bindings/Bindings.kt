package io.elixxir.xxclient.bindings

import io.elixxir.xxclient.backup.Backup
import io.elixxir.xxclient.callbacks.AuthEventListener
import io.elixxir.xxclient.channel.Channel
import io.elixxir.xxclient.cmix.CMix
import io.elixxir.xxclient.dummytraffic.DummyTraffic
import io.elixxir.xxclient.e2e.E2e
import io.elixxir.xxclient.filetransfer.FileTransfer
import io.elixxir.xxclient.models.*
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

    fun newDummyTrafficManager(): DummyTraffic

    fun registerLogger(logLevel: LogLevel, logWriter: LogWriter)

    fun newBackupManager(): Backup

    fun initializeBackup(): Backup

    fun resumeBackup(): Backup

    fun fetchSignedNdf(
        url: String,
        cert: String
    ): Ndf

    fun getReceptionIdentity(
        key: String,
        e2eId: E2eId
    ): ReceptionIdentity

    fun newFileTransferManager(

    ): FileTransfer

    fun getIdFromContact(contactData: ContactData): ByteArray
    fun getPublicKeyFromContact(contactData: ContactData): ByteArray
    fun getFactsFromContact(contactData: ContactData): Fact
    fun setFactsOnContact(contactData: ContactData, fact: Fact): ContactData

    fun searchUd(): ByteArray

    fun lookupUd(): ByteArray

    fun newBroadcastChannel(): Channel

    fun storeReceptionIdentity()

    fun transmitSingleUse(): ByteArray

    fun updateCommonErrors(errorsJson: String)
}