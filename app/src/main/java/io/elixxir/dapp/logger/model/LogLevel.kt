package io.elixxir.dapp.logger.model

enum class LogLevel(val code: Int) {
    None(Int.MAX_VALUE),
    Trace(0),
    Debug(1),
    Info(2),
    Warn(3),
    Error(4),
    Critical(5),
    Fatal(6);

    companion object {
        fun from(code: Int): LogLevel {
            return values().firstOrNull {
                it.code == code
            } ?: throw(IllegalArgumentException("Invalid code"))
        }
    }
}