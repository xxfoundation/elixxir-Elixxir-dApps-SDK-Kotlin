package io.elixxir.dapp.api.model

interface AccountApi {
    fun getCurrentUser()
    fun createAccount(username: String)
    fun restoreAccount()
    fun updateAccount()
    fun deleteAccount()
}