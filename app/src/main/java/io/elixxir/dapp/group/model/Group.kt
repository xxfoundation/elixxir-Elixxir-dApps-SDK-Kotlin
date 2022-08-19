package io.elixxir.dapp.group.model

import io.elixxir.dapp.user.model.User

interface Group {
    val groupId: GroupId
    val name: String
    val description: String
    val members: List<User>
    val creator: User
}