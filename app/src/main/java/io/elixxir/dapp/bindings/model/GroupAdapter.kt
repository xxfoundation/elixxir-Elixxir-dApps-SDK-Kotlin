package io.elixxir.dapp.bindings.model

import io.elixxir.dapp.group.model.Group
import io.elixxir.dapp.group.model.GroupId
import io.elixxir.dapp.user.model.User
import bindings.Group as CoreGroup

@JvmInline
internal value class GroupAdapter(val value: CoreGroup): Group {
    override val groupId: GroupId
        get() = GroupId(value.id)
    override val name: String
        get() = String(value.name)
    override val description: String
        get() = String(value.initMessage)
    override val members: List<User>
        get() = listOf()
    override val creator: User
        get() = User.placeholder
}