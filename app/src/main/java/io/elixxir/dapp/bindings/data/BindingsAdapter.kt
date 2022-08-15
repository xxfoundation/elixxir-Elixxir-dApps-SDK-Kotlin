package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.session.model.SessionPassword
import bindings.Bindings as CoreBindings

internal class BindingsAdapter: Bindings {

    override fun generateSecret(byteLength: Long): SessionPassword {
        return SessionPassword(CoreBindings.generateSecret(byteLength))
    }
}