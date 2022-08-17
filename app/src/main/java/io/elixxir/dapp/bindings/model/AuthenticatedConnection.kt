package io.elixxir.dapp.bindings.model

internal interface AuthenticatedConnection : Connection {
    fun isAuthenticated()
}