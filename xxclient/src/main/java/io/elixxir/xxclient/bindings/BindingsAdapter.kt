package io.elixxir.xxclient.bindings

import io.elixxir.xxclient.backup.Backup
import io.elixxir.xxclient.backup.BackupAdapter
import io.elixxir.xxclient.callbacks.*
import io.elixxir.xxclient.callbacks.BackupUpdateListener
import io.elixxir.xxclient.callbacks.UpdateBackupFuncAdapter
import io.elixxir.xxclient.channel.Channel
import io.elixxir.xxclient.channel.ChannelAdapter
import io.elixxir.xxclient.cmix.CMix
import io.elixxir.xxclient.cmix.CMixAdapter
import io.elixxir.xxclient.dummytraffic.DummyTraffic
import io.elixxir.xxclient.dummytraffic.DummyTrafficAdapter
import io.elixxir.xxclient.e2e.E2e
import io.elixxir.xxclient.e2e.E2eAdapter
import io.elixxir.xxclient.filetransfer.FileTransfer
import io.elixxir.xxclient.filetransfer.FileTransferAdapter
import io.elixxir.xxclient.models.*
import io.elixxir.xxclient.models.BindingsModel.Companion.decode
import io.elixxir.xxclient.models.BindingsModel.Companion.encode
import io.elixxir.xxclient.models.ReceiveFileCallbackAdapter
import io.elixxir.xxclient.userdiscovery.UserDiscovery
import io.elixxir.xxclient.userdiscovery.UserDiscoveryAdapter
import io.elixxir.xxclient.utils.*
import bindings.Bindings as CoreBindings

open class BindingsAdapter : Bindings {
    override val defaultCmixParams: CmixParams
        get() = CoreBindings.getDefaultCMixParams()
    override val defaultE2eParams: E2eParams
        get() = CoreBindings.getDefaultE2EParams()
    override val defaultE2eFileTransferParams: E2eFileTransferParams
        get() = CoreBindings.getDefaultE2eFileTransferParams()
    override val defaultFileTransferParams: FileTransferParams
        get() = CoreBindings.getDefaultFileTransferParams()
    override val defaultSingleUseParams: SingleUseParams
        get() = CoreBindings.getDefaultSingleUseParams()
    override val gitVersion: String
        get() = CoreBindings.getGitVersion()
    override val dependencies: String
        get() = CoreBindings.getDependencies()

    override fun downloadAndVerifySignedNdf(
        environmentUrl: String,
        certificate: Certificate
    ): Ndf {
        return CoreBindings.downloadAndVerifySignedNdfWithUrl(environmentUrl, certificate)
    }

    override fun generateSecret(byteLength: Long): Password {
        return CoreBindings.generateSecret(byteLength)
    }

    override fun loadCmix(
        sessionFileDirectory: String,
        sessionPassword: Password,
        cmixParams: CmixParams
    ): CMix {
        return CMixAdapter(
            CoreBindings.loadCmix(sessionFileDirectory, sessionPassword, cmixParams)
        )
    }

    override fun login(
        e2eId: E2eId,
        authCallbacks: AuthEventListener,
        receptionIdentity: ReceptionIdentity,
        e2eParams: E2eParams
    ): E2e {
        return E2eAdapter(
            CoreBindings.login(
                e2eId,
                AuthCallbacksAdapter(authCallbacks),
                encode(receptionIdentity),
                e2eParams
            )
        )
    }

    override fun loginEphemeral(
        e2eId: E2eId,
        authCallbacks: AuthEventListener,
        receptionIdentity: ReceptionIdentity,
        e2eParams: E2eParams
    ): E2e {
        return E2eAdapter(
            CoreBindings.loginEphemeral(
                e2eId,
                AuthCallbacksAdapter(authCallbacks),
                encode(receptionIdentity),
                e2eParams
            )
        )
    }

    override fun getOrCreateUd(
        e2eId: E2eId,
        networkFollowerStatus: NetworkFollowerStatus,
        username: String,
        signature: Signature,
        udCert: CertificateData,
        contact: Contact,
        udIpAddress: String
    ): UserDiscovery {
        return UserDiscoveryAdapter(
            CoreBindings.newOrLoadUd(
                e2eId,
                { networkFollowerStatus.code },
                username,
                signature,
                udCert,
                contact.encoded(),
                udIpAddress
            )
        )
    }

    override fun newUdFromBackup(
        e2eId: E2eId,
        networkFollowerStatus: NetworkFollowerStatus,
        emailFact: Fact,
        phoneFact: Fact,
        udCert: CertificateData,
        contact: Contact,
        udAddress: ByteArray,
        stringArg: String
    ): UserDiscovery {
        return UserDiscoveryAdapter(
            CoreBindings.newUdManagerFromBackup(
                e2eId,
                { networkFollowerStatus.code },
                encode(emailFact),
                encode(phoneFact),
                udCert,
                contact.encoded(),
                udAddress,
                stringArg
            )
        )
    }

    override fun newDummyTrafficManager(
        cmixId: Long,
        maxNumMessages: Long,
        avgSendDeltaMS: Long,
        randomRangeMS: Long
    ): DummyTraffic {
        return DummyTrafficAdapter(
            CoreBindings.newDummyTrafficManager(
                cmixId,
                maxNumMessages,
                avgSendDeltaMS,
                randomRangeMS)
        )
    }

    override fun registerLogger(logLevel: LogLevel, logWriter: LogWriter) {
        CoreBindings.logLevel(logLevel.code)
        CoreBindings.registerLogWriter(logWriter)
    }

    override fun initializeBackup(
        e2eId: E2eId,
        udId: Long,
        backupPassword: String,
        updateListener: BackupUpdateListener
    ): Backup {
        return BackupAdapter(
            CoreBindings.initializeBackup(
                e2eId,
                udId,
                backupPassword,
                UpdateBackupFuncAdapter(updateListener)
            )
        )
    }

    override fun resumeBackup(
        e2eId: E2eId,
        udId: Long,
        updateListener: BackupUpdateListener
    ): Backup {
        return BackupAdapter(
            CoreBindings.resumeBackup(
                e2eId,
                udId,
                UpdateBackupFuncAdapter(updateListener)
            )
        )
    }

    override fun fetchSignedNdf(url: String, cert: String): Ndf {
        return CoreBindings.downloadAndVerifySignedNdfWithUrl(url, cert)
    }

    override fun getReceptionIdentity(key: String, e2eId: E2eId): ReceptionIdentity {
        return decode(
            CoreBindings.loadReceptionIdentity(key, e2eId),
            ReceptionIdentity::class.java
        )
    }

    override fun newFileTransferManager(
        e2eId: E2eId,
        incomingFileListener: IncomingFileListener,
        e2eFileTransferParamsJson: ByteArray,
        fileTransferParamsJson: ByteArray
    ): FileTransfer {
        return FileTransferAdapter(
            CoreBindings.initFileTransfer(
                e2eId,
                ReceiveFileCallbackAdapter(incomingFileListener),
                e2eFileTransferParamsJson,
                fileTransferParamsJson
            )
        )
    }

    override fun getIdFromContact(contactData: ContactData): ByteArray {
        return CoreBindings.getIDFromContact(contactData)
    }

    override fun getPublicKeyFromContact(contactData: ContactData): ByteArray {
        return CoreBindings.getPubkeyFromContact(contactData)
    }

    override fun getFactsFromContact(contactData: ContactData): Fact {
        return decode(
            CoreBindings.getFactsFromContact(contactData),
            Fact::class.java
        )
    }

    override fun setFactsOnContact(contactData: ContactData, fact: Fact): ContactData {
        return CoreBindings.setFactsOnContact(contactData, encode(fact))
    }

    override fun searchUd(
        e2eId: E2eId,
        udContact: Contact,
        listener: UdSearchResultListener,
        factsListJson: ByteArray,
        singleRequestParamsJson: ByteArray
    ): ContactList {
        val result = CoreBindings.searchUD(
                e2eId,
                udContact.encoded(),
                UdSearchCallbackAdapter(listener),
                factsListJson,
                singleRequestParamsJson
            )
        return decode(result, ContactList::class.java)
    }

    override fun lookupUd(
        e2eId: E2eId,
        udContact: Contact,
        listener: UdLookupResultListener,
        lookupId: UserId,
        singleRequestParamsJson: ByteArray
    ): SingleUseReport {
        val result = CoreBindings.lookupUD(
            e2eId,
            udContact.encoded(),
            UdLookupCallbackAdapter(listener),
            lookupId,
            singleRequestParamsJson
        )
        return decode(result, SingleUseReport::class.java)
    }

    override fun multiLookupUd(
        e2eId: E2eId,
        udContact: Contact,
        listener: UdMultiLookupResultListener,
        lookupIds: BindingsList<UserId>,
        singleRequestParamsJson: ByteArray
    ) {
        CoreBindings.multiLookupUD(
            e2eId,
            udContact.encoded(),
            UdMultiLookupCallbackAdapter(listener),
            lookupIds.encoded(),
            singleRequestParamsJson
        )
    }

    override fun newBroadcastChannel(cmixId: Long, channelDef: ChannelDef): Channel {
        return ChannelAdapter(
            CoreBindings.newBroadcastChannel(cmixId, encode(channelDef))
        )
    }

    override fun storeReceptionIdentity(
        key: String,
        identity: ReceptionIdentity,
        cmixId: Long
    ) {
        CoreBindings.storeReceptionIdentity(
            key,
            encode(identity),
            cmixId
        )
    }

    override fun transmitSingleUse(
        e2eId: E2eId,
        recipient: Contact,
        tag: String,
        payload: Payload,
        paramsJson: ByteArray,
        listener: SingleUseResponseListener
    ): SingleUseReport {
        val result = CoreBindings.transmitSingleUse(
            e2eId,
            recipient.encoded(),
            tag,
            payload,
            paramsJson,
            SingleUseResponseAdapter(listener)
        )
        return decode(result, SingleUseReport::class.java)
    }

    override fun updateCommonErrors(errorsJson: String) {
        CoreBindings.updateCommonErrors(errorsJson)
    }

    override fun isRegisteredWithUd(e2eId: E2eId): Boolean {
        return CoreBindings.isRegisteredWithUD(e2eId)
    }
}