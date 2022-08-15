package io.elixxir.dapp.user.model

interface User {
    val userId: Long
    val username: String
    val phone: String
    val email: String
}