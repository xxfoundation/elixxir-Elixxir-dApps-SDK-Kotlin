package io.elixxir.xxclient.models

enum class FilePartStatus(val code: Long) {
    DoesNotExist(Long.MIN_VALUE),
    Unsent(0),
    Arrived(1),
    Received(2),
    Unknown(Long.MAX_VALUE);

    companion object {
        fun from(code: Long): FilePartStatus {
            return when {
                code < 0 -> DoesNotExist
                else -> {
                    values().firstOrNull {
                        it.code == code
                    } ?: Unknown
                }
            }
        }
    }
}