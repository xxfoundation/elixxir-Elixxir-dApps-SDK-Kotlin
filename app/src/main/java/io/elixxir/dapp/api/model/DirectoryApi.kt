package io.elixxir.dapp.api.model

import io.elixxir.dapp.user.model.User
import io.elixxir.dapp.user.model.UserQuery

interface DirectoryApi {
    fun getContacts(): List<User>
    fun findUser(params: UserQuery): User?
    fun blockUser(user: User)
}