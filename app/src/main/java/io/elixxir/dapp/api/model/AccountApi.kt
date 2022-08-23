package io.elixxir.dapp.api.model

import io.elixxir.dapp.backup.model.Backup
import io.elixxir.dapp.user.model.User
import io.elixxir.dapp.user.model.UserUpdateData

interface AccountApi {
    fun getCurrentUser(): User
    fun createAccount(username: String)
    fun restoreAccount(backup: Backup)
    fun updateAccount(updateData: UserUpdateData)
    fun deleteAccount()
}