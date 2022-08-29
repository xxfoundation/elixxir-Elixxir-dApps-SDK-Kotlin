package io.elixxir.xxclient.models

import io.elixxir.xxclient.utils.RoundId

data class SendReport(
    val roundIdList: List<RoundId>?,
    val messageId: ByteArray?,
    val timestamp: Long?
) : BindingsModel