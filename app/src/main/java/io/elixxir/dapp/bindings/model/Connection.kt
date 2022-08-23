package io.elixxir.dapp.bindings.model

internal interface Connection {
    fun getId(): Long
    fun getPartner(): ByteArray
    fun registerListener()
    fun sendE2e()
    fun close()
}