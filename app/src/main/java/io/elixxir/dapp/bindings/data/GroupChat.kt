package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.bindings.model.GroupInfo
import io.elixxir.dapp.bindings.model.GroupReport
import io.elixxir.dapp.group.model.Group
import io.elixxir.dapp.group.model.GroupId

internal interface GroupChat {
    fun getGroup(groupId: GroupId): Group
    fun joinGroup(trackedGroupId: Long)
    fun leaveGroup(groupId: GroupId)
    fun makeGroup(info: GroupInfo): GroupReport
    fun resendInvitations(group: Group): GroupReport
}