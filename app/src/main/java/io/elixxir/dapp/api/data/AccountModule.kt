package io.elixxir.dapp.api.data

import io.elixxir.dapp.api.model.AccountApi
import io.elixxir.dapp.session.repository.SessionDataSource
import kotlinx.coroutines.*

internal class AccountModule(
    private val sessionManager: SessionDataSource
) : AccountApi {
    private val scope: CoroutineScope = CoroutineScope(
        CoroutineName("AccountModule")
                + Job()
                + Dispatchers.Default
    )

    override fun getCurrentUser() {
        TODO("Not yet implemented")
    }

    override fun createAccount(username: String) {
        scope.launch {
            sessionManager.createSession()
        }
    }

    override fun restoreAccount() {
        scope.launch {
            sessionManager.restoreSession()
        }
    }

    override fun updateAccount() {
        TODO("Not yet implemented")
    }

    override fun deleteAccount() {
        scope.launch {
            sessionManager.deleteSession()
        }
    }
}