package io.elixxir.dapp.user.repository

import io.elixxir.dapp.bindings.data.UserDiscovery
import io.elixxir.dapp.api.model.CommonProperties
import io.elixxir.dapp.user.model.User

interface UserDataSource {
    suspend fun createUser(username: String): Result<User>
    suspend fun updateProfile(): Result<User>
    suspend fun deleteProfile(): Result<Unit>
}

internal class RemoteUserDataSource private constructor(
    properties: CommonProperties,
    private val ud: UserDiscovery
) : UserDataSource, CommonProperties by properties {

    override suspend fun createUser(username: String): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun updateProfile(): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProfile(): Result<Unit> {
        TODO("Not yet implemented")
    }

    companion object {
        internal fun newInstance(
            properties: CommonProperties,
            ud: UserDiscovery
        ) = RemoteUserDataSource(properties, ud)
    }
}