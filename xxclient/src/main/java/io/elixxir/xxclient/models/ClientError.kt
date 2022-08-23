package io.elixxir.xxclient.models

data class ClientError(
    val source: String?,
    val message: String?,
    val trace: String?
)