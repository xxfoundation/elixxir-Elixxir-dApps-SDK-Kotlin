package io.elixxir.xxclient.models

enum class LogLevel(val code: Long) {
    Trace(0),
    Debug(1),
    Info(2),
    Warn(3),
    Error(4),
    Critical(5),
    Fatal(6),
    None(Long.MAX_VALUE);

    companion object {
        fun from(code: Long): LogLevel {
            return values().firstOrNull {
                it.code == code
            } ?: throw(IllegalArgumentException("Invalid code"))
        }
    }
}
