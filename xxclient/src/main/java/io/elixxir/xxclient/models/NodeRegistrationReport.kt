package io.elixxir.xxclient.models

data class NodeRegistrationReport(
    val registeredCount: Long,
    val totalCount: Long
) : BindingsModel {
    val ratio: Float get() = registeredCount.toFloat() / totalCount.toFloat()
}

