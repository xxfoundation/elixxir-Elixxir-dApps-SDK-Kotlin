package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.session.model.SessionPassword

internal interface Bindings {
    fun generateSecret(byteLength: Long): SessionPassword
}

