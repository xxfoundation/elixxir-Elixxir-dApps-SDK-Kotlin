package io.elixxir.xxclient.cmix

import io.elixxir.xxclient.callbacks.*
import io.elixxir.xxclient.connection.Connection
import io.elixxir.xxclient.connection.ConnectionAdapter
import io.elixxir.xxclient.models.BindingsModel.Companion.decode
import io.elixxir.xxclient.models.NetworkFollowerStatus
import io.elixxir.xxclient.models.NodeRegistrationReport
import io.elixxir.xxclient.utils.E2eId
import bindings.Cmix as CMixBindings

interface CMix {
    val id: E2eId
    val receptionRegistrationValidationSignature: ByteArray

    fun makeReceptionIdentity(): ByteArray
    fun makeLegacyReceptionIdentity(): ByteArray
    fun isNetworkHealthy(): Boolean
    fun getNodeRegistrationStatus(): NodeRegistrationReport?
    fun hasRunningProcesses(): Boolean
    fun getNetworkFollowerStatus(): NetworkFollowerStatus
    fun startNetworkFollower(timeoutMs: Long)
    fun stopNetworkFollower()
    fun waitForNetwork(timeoutMs: Long): Boolean
    fun setClientErrorListener(listener: ClientErrorListener)
    fun setHealthListener(listener: NetworkHealthListener)
    fun waitForRoundResult(
        roundList: ByteArray,
        timeoutMs: Long,
        listener: MessageDeliveryListener
    )
    fun connect(
        withAuthentication: Boolean,
        e2eId: Long,
        recipientContactData: ByteArray,
        e2eParamsJson: ByteArray
    ): Connection
}

open class CMixAdapter(protected val cMix: CMixBindings) : CMix {
    override val id: E2eId
        get() = cMix.id

    override val receptionRegistrationValidationSignature: ByteArray
        get() = cMix.receptionRegistrationValidationSignature

    override fun makeReceptionIdentity(): ByteArray {
        return cMix.makeReceptionIdentity()
    }

    override fun makeLegacyReceptionIdentity(): ByteArray {
        return cMix.makeLegacyReceptionIdentity()
    }

    override fun isNetworkHealthy(): Boolean {
        return cMix.isHealthy
    }

    override fun getNodeRegistrationStatus(): NodeRegistrationReport? {
        return decode(
            cMix.nodeRegistrationStatus
        )
    }

    override fun hasRunningProcesses(): Boolean {
        return cMix.hasRunningProcessies()
    }

    override fun getNetworkFollowerStatus(): NetworkFollowerStatus {
        return NetworkFollowerStatus.from(
            cMix.networkFollowerStatus()
        )
    }

    override fun startNetworkFollower(timeoutMs: Long) {
        cMix.startNetworkFollower(timeoutMs)
    }

    override fun stopNetworkFollower() {
        cMix.stopNetworkFollower()
    }

    override fun waitForNetwork(timeoutMs: Long): Boolean {
        return cMix.waitForNetwork(timeoutMs)
    }

    override fun setClientErrorListener(listener: ClientErrorListener) {
        cMix.registerClientErrorCallback(
            ClientErrorCallbackAdapter(listener)
        )
    }

    override fun setHealthListener(listener: NetworkHealthListener) {
        cMix.addHealthCallback(
            HealthCallbackAdapter(listener)
        )
    }

    override fun waitForRoundResult(
        roundList: ByteArray,
        timeoutMs: Long,
        listener: MessageDeliveryListener
    ) {
        cMix.waitForRoundResult(
            roundList,
            MessageDeliveryCallbackAdapter(listener),
            timeoutMs,
        )
    }

    override fun connect(
        withAuthentication: Boolean,
        e2eId: Long,
        recipientContactData: ByteArray,
        e2eParamsJson: ByteArray
    ): Connection {
        return ConnectionAdapter(
            withAuthentication,
            cMix.connect(
                e2eId,
                recipientContactData,
                e2eParamsJson
            )
        )
    }
}