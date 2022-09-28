package io.elixxir.xxclient.group

import bindings.Group as GroupBindings

interface Group {
    val createdMs: Long
    val createdNano:Long
    val id: ByteArray
    val initMessage: ByteArray
    val membership:ByteArray
    val name: ByteArray
//    val trackedId: Long
    val serialize: ByteArray
}

open class GroupAdapter(
    protected val group: GroupBindings
) : Group {
    override val createdMs: Long
        get() = group.createdMS
    override val createdNano: Long
        get() = group.createdNano
    override val id: ByteArray
        get() = group.id
    override val initMessage: ByteArray
        get() = group.initMessage
    override val membership: ByteArray
        get() = group.membership
    override val name: ByteArray
        get() = group.name
//    override val trackedId: Long
//        get() = group.trackedID
    override val serialize: ByteArray
        get() = group.serialize()
}

