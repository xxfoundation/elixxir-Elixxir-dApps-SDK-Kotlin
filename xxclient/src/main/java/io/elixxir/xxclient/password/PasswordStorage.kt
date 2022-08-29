package io.elixxir.xxclient.password

interface PasswordStorage {
    fun save(password: ByteArray)
    fun load(): ByteArray
}