package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.bindings.model.GroupAdapter
import io.elixxir.dapp.bindings.model.GroupInfo
import io.elixxir.dapp.bindings.model.GroupReport
import io.elixxir.dapp.group.model.Group
import io.elixxir.dapp.group.model.GroupId
import bindings.GroupChat as CoreGroupChat

internal class GroupChatAdapter(private val groupChat: CoreGroupChat) : GroupChat {
    override fun getGroup(groupId: GroupId): Group {
         return GroupAdapter(groupChat.getGroup(groupId.value))
    }

    override fun joinGroup(trackedGroupId: Long) {
        groupChat.joinGroup(trackedGroupId)
    }

    override fun leaveGroup(groupId: GroupId) {
        groupChat.leaveGroup(groupId.value)
    }

    override fun makeGroup(info: GroupInfo): GroupReport {
        return GroupReport(
            groupChat.makeGroup(
                info.membershipData.value,
                info.description.toByteArray(),
                info.name.toByteArray())
        )
    }

    override fun resendInvitations(group: Group): GroupReport {
        return GroupReport(
            groupChat.resendRequest(group.groupId.value)
        )
    }
}