package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.bindings.model.*
import io.elixxir.dapp.bindings.model.Contact
import io.elixxir.dapp.bindings.model.Fact
import io.elixxir.dapp.bindings.model.FactsList
import bindings.UserDiscovery as CoreUserDiscovery

@JvmInline
internal value class UserDiscoveryAdapter(private val ud: CoreUserDiscovery) : UserDiscovery {

    override fun getContact(): Contact {
        return Contact(ud.contact)
    }

    override fun getFacts(): FactsList {
        return FactsList(ud.facts)
    }

    override fun registerUsername(username: String) {
        TODO("Not yet implemented")
    }

    override fun registerEmail(email: String): ConfirmationId{
        return ConfirmationId(
            ud.sendRegisterFact(Fact.placeholder)
        )
    }

    override fun registerPhone(phone: String): ConfirmationId {
        return ConfirmationId(
            ud.sendRegisterFact(Fact.placeholder)
        )
    }

    override fun confirmTwoFactorAuth(
        confirmationId: ConfirmationId,
        confirmationCode: ConfirmationCode
    ) {
        ud.confirmFact(
            confirmationId.value,
            confirmationCode.value
        )
    }

    override fun removeEmail() {
        ud.removeFact(Fact.placeholder)
    }

    override fun removePhone() {
        ud.removeFact(Fact.placeholder)
    }

    override fun deleteUser() {
        ud.permanentDeleteAccount(ud.contact)
    }
}