package io.elixxir.dapp.bindings.model

internal enum class NetworkFollowerStatus(val code: Long) {
    STOPPED(0),
    STARTING(1000),
    RUNNING(2000),
    STOPPING(3000);

    companion object {
        fun from(code: Long) = values().first { it.code == code }
    }
}