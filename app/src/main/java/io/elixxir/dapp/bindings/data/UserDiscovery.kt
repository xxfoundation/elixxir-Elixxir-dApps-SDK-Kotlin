package io.elixxir.dapp.bindings.data

interface UserDiscovery {
    fun registerUsername(username: String)
    fun registerNickname(nickname: String)
    fun registerEmail(email: String)
    fun registerPhone(phone: String)
    fun confirmTwoFactorAuth(tfaId: String, tfaCode: String)
    fun removeEmail()
    fun removePhone()
    fun deleteUser()
    fun findUserById(id: ByteArray)
    fun usernameSearch(username: String)
    fun phoneSearch(phone: String)
    fun emailSearch(email: String)
}