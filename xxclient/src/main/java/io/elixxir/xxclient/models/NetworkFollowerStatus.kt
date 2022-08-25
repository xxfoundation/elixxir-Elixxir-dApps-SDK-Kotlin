package io.elixxir.xxclient.models

enum class NetworkFollowerStatus(val code: Long) {
    Stopped(0),
    Running(2000),
    Stopping(3000),
    Unknown(Long.MAX_VALUE);

    companion object {
        fun from(code: Long): NetworkFollowerStatus {
            return NetworkFollowerStatus.values().firstOrNull {
                it.code == code
            } ?: Unknown
        }
    }
}