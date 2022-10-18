package io.elixxir.xxclient.models

sealed class FactType {

    object Username : FactType()
    object Email : FactType()
    object Phone : FactType()
    class Other(val code: Long) : FactType()

    companion object {
        fun from(code: Long): FactType = when (code) {
            0L -> Username
            1L -> Email
            2L -> Phone
            else -> Other(code)
        }
    }
}