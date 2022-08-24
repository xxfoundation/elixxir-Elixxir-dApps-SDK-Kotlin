package io.elixxir.xxclient.models

data class Progress(
    val completed: Boolean,
    val transmitted: Long,
    val total: Long,
    val error: String?
) : BindingsModel