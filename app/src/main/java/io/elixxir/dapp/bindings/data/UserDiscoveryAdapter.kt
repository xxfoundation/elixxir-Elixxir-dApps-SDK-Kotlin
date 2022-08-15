package io.elixxir.dapp.bindings.data

import bindings.UserDiscovery as CoreUserDiscovery

@JvmInline
internal value class UserDiscoveryAdapter(private val ud: CoreUserDiscovery) : UserDiscovery {
    override fun registerUsername(username: String) {
        TODO("Not yet implemented")
    }

    override fun registerNickname(nickname: String) {
        TODO("Not yet implemented")
    }

    override fun registerEmail(email: String) {
        TODO("Not yet implemented")
    }

    override fun registerPhone(phone: String) {
        TODO("Not yet implemented")
    }

    override fun confirmTwoFactorAuth(tfaId: String, tfaCode: String) {
        TODO("Not yet implemented")
    }

    override fun removeEmail() {
        TODO("Not yet implemented")
    }

    override fun removePhone() {
        TODO("Not yet implemented")
    }

    override fun deleteUser() {
        TODO("Not yet implemented")
    }

    override fun findUserById(id: ByteArray) {
        TODO("Not yet implemented")
    }

    override fun usernameSearch(username: String) {
        TODO("Not yet implemented")
    }

    override fun phoneSearch(phone: String) {
        TODO("Not yet implemented")
    }

    override fun emailSearch(email: String) {
        TODO("Not yet implemented")
    }
}