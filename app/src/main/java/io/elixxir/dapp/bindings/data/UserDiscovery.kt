package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.bindings.model.ConfirmationCode
import io.elixxir.dapp.bindings.model.ConfirmationId
import io.elixxir.dapp.bindings.model.Contact
import io.elixxir.dapp.bindings.model.FactsList

internal interface UserDiscovery {
    fun getContact(): Contact

    fun getFacts(): FactsList

    fun registerUsername(username: String)

    fun registerEmail(email: String): ConfirmationId

    fun registerPhone(phone: String): ConfirmationId

    fun confirmTwoFactorAuth(
        confirmationId: ConfirmationId,
        confirmationCode: ConfirmationCode
    )

    fun removeEmail()

    fun removePhone()

    fun deleteUser()
}