package io.elixxir.dapp.bindings.data

import io.elixxir.dapp.group.model.GroupId
import bindings.GroupChat as CoreGroupChat

internal class GroupChatAdapter(private val groupChat: CoreGroupChat) : GroupChat {
    override fun getGroup(groupId: GroupId) {
        TODO("Not yet implemented")
    }

    override fun joinGroup() {
        TODO("Not yet implemented")
    }

    override fun leaveGroup() {
        TODO("Not yet implemented")
    }

    override fun makeGroup() {
        TODO("Not yet implemented")
    }

    override fun resendInvitation() {
        TODO("Not yet implemented")
    }
}