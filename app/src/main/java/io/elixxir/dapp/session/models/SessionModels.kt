package io.elixxir.dapp.session.models

import bindings.Cmix
import bindings.E2e

@JvmInline
value class SessionPassword(val value: ByteArray)

@JvmInline
value class CmixMediator(private val value: Cmix)

@JvmInline
value class E2eMediator(private val value: E2e)

@JvmInline
value class ReceptionIdentity(val value: ByteArray)

@JvmInline
value class E2eParams(val value: ByteArray)

enum class Environment {
    MainNet, ReleaseNet
}