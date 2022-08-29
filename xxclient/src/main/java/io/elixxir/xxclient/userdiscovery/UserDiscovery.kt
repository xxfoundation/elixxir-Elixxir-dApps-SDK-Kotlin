package io.elixxir.xxclient.userdiscovery

import io.elixxir.xxclient.models.BindingsModel.Companion.encode
import io.elixxir.xxclient.models.Contact
import io.elixxir.xxclient.models.ContactAdapter
import io.elixxir.xxclient.models.Fact
import io.elixxir.xxclient.utils.ConfirmationId
import io.elixxir.xxclient.utils.VerificationCode
import bindings.UserDiscovery as UdBindings

interface UserDiscovery {
    val id: Long
    val contact: Contact
    val facts: ByteArray

    fun sendRegisterFact(fact: Fact): ConfirmationId
    fun confirmFact(confirmationId: ConfirmationId, code: VerificationCode)
    fun removeFact(fact: Fact)
    fun permanentDeleteAccount(username: Fact)
}

open class UserDiscoveryAdapter(protected val ud: UdBindings) : UserDiscovery {
    override val id: Long
        get() = ud.id
    override val contact: Contact
        get() = ContactAdapter(ud.contact)
    override val facts: ByteArray
        get() = ud.facts

    override fun sendRegisterFact(fact: Fact): ConfirmationId {
        return ud.sendRegisterFact(encode(fact))
    }

    override fun confirmFact(confirmationId: ConfirmationId, code: VerificationCode) {
        ud.confirmFact(confirmationId, code)
    }

    override fun removeFact(fact: Fact) {
        ud.removeFact(encode(fact))
    }

    override fun permanentDeleteAccount(username: Fact) {
        ud.permanentDeleteAccount(encode(username))
    }
}