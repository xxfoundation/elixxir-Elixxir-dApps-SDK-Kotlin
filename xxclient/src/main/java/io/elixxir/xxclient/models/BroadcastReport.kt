package io.elixxir.xxclient.models

data class BroadcastReport(
    val roundId: Long,
    val ephemeralId: Long
) : BindingsModel