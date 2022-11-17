package io.elixxir.xxclient.models

sealed class FactType {

    object Username : FactType()
    object Email : FactType()
    object Phone : FactType()
    class Other(val code: Int) : FactType()

    companion object {
        fun from(code: Int): FactType = when (code) {
            0 -> Username
            1 -> Email
            2 -> Phone
            else -> Other(code)
        }
    }
}