package io.elixxir.dapp.ud.repository

interface UserDataSource {
    fun createUser(username: String)
}