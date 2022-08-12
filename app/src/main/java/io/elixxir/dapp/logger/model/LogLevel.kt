package io.elixxir.dapp.logger.model

enum class LogLevel(val code: Int) {
    Trace(0),
    Debug(1),
    Info(2),
    Warn(3),
    Error(4),
    Critical(5),
    Fatal(6),
    None(Int.MAX_VALUE);

    companion object {
        fun from(code: Int): LogLevel {
            return values().firstOrNull {
                it.code == code
            } ?: throw(IllegalArgumentException("Invalid code"))
        }
    }
}