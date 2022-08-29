package io.elixxir.xxclient.password

import io.elixxir.xxclient.utils.Password

interface PasswordStorage {
    fun save(password: Password)
    fun load(): Password
}