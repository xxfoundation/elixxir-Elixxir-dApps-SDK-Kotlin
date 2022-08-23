package io.elixxir.dapp.user.model

interface User {
    val userId: Long
    val username: String
    val phone: String?
    val email: String?

    companion object {
        val placeholder = object : User {
            override val userId: Long = 0
            override val username: String = ""
            override val phone: String? = null
            override val email: String? = null

        }
    }
}