package io.elixxir.dapp.bindings.model

import bindings.Connection as CoreConnection

@JvmInline
value class ConnectionAdapter(private val connection: CoreConnection) : Connection {
    override fun getId(): Long {
        TODO("Not yet implemented")
    }

    override fun getPartner(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun registerListener() {
        TODO("Not yet implemented")
    }

    override fun sendE2e() {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}