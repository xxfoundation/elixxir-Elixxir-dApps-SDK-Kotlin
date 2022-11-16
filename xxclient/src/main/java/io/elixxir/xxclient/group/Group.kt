package io.elixxir.xxclient.group

import io.elixxir.xxclient.models.BindingsModel.Companion.decodeArray
import io.elixxir.xxclient.models.GroupMember
import bindings.Group as GroupBindings

interface Group {
    val createdMs: Long
    val createdNano: Long
    val id: ByteArray
    val initMessage: ByteArray
    val membership: List<GroupMember>
    val name: ByteArray
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
    override val membership: List<GroupMember> by lazy { decodeArray(group.membership) }
    override val name: ByteArray
        get() = group.name
    override val serialize: ByteArray by lazy { group.serialize() }
}

