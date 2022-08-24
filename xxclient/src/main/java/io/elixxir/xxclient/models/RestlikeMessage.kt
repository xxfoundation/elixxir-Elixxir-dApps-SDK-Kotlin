package io.elixxir.xxclient.models

data class RestlikeMessage(
    val version: Long,
    val headers: ByteArray,
    val content: ByteArray,
    val method: Long,
    val uri: String?,
    val error: String?
) : BindingsModel