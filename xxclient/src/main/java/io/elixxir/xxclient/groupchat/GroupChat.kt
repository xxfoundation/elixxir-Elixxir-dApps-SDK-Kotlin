package io.elixxir.xxclient.groupchat

import bindings.GroupChat as GroupChatBindings
import io.elixxir.xxclient.group.Group
import io.elixxir.xxclient.group.GroupAdapter
import io.elixxir.xxclient.models.BindingsModel.Companion.decode
import io.elixxir.xxclient.models.BindingsModel.Companion.decodeArray
import io.elixxir.xxclient.models.GroupReport
import io.elixxir.xxclient.models.GroupSendReport

interface GroupChat {
    val numGroups: Long

    fun getGroup(groupId: ByteArray): Group
    fun getGroups(): List<Group>
    fun makeGroup(membership: ByteArray, message: ByteArray, name: ByteArray): GroupReport
    fun joinGroup(trackedGroupId: ByteArray)
    fun leaveGroup(groupId: ByteArray)
    fun resendRequest(groupId: ByteArray): GroupReport
    fun send(groupId: ByteArray, message: ByteArray, tag: String?): GroupSendReport
}

open class GroupChatAdapter(
    protected val group: GroupChatBindings
) : GroupChat {
    override val numGroups: Long
        get() = group.numGroups()

    override fun getGroup(groupId: ByteArray): Group {
        return GroupAdapter(
            group.getGroup(groupId)
        )
    }

    override fun getGroups(): List<Group> {
        return decodeArray(group.groups)
    }

    override fun makeGroup(
        membership: ByteArray,
        message: ByteArray,
        name: ByteArray
    ): GroupReport {
        return decode(
            group.makeGroup(membership, message, name)
        )
    }

    override fun joinGroup(trackedGroupId: ByteArray) {
        group.joinGroup(trackedGroupId)
    }

    override fun leaveGroup(groupId: ByteArray) {
        group.leaveGroup(groupId)
    }

    override fun resendRequest(groupId: ByteArray): GroupReport {
        return decode(
            group.resendRequest(groupId)
        )
    }

    override fun send(groupId: ByteArray, message: ByteArray, tag: String?): GroupSendReport {
        return decode(
            group.send(groupId, message, tag)
        )
    }
}