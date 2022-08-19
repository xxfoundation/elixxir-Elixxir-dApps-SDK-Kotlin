package io.elixxir.dapp.api.data

import io.elixxir.dapp.api.model.AccountApi
import io.elixxir.dapp.backup.model.Backup
import io.elixxir.dapp.session.repository.SessionDataSource
import io.elixxir.dapp.user.model.User
import io.elixxir.dapp.user.model.UserUpdateData
import kotlinx.coroutines.*

internal class AccountModule(
    private val sessionManager: SessionDataSource
) : AccountApi {
    private val scope: CoroutineScope = CoroutineScope(
        CoroutineName("AccountModule")
                + Job()
                + Dispatchers.Default
    )

    override fun getCurrentUser(): User {
        TODO("Not yet implemented")
    }

    override fun createAccount(username: String) {
        scope.launch {
            sessionManager.createSession()
        }
    }

    override fun restoreAccount(backup: Backup) {
        TODO("Not yet implemented")
    }

    override fun updateAccount(updateData: UserUpdateData) {
        TODO("Not yet implemented")
    }

    override fun deleteAccount() {
        scope.launch {
            sessionManager.deleteSession()
        }
    }
}