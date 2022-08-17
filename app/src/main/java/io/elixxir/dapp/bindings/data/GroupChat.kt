package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.group.model.GroupId

internal interface GroupChat {
    fun getGroup(groupId: GroupId)
    fun joinGroup()
    fun leaveGroup()
    fun makeGroup()
    fun resendInvitation()
}