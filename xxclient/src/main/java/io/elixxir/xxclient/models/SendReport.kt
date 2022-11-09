package io.elixxir.xxclient.models

import com.google.gson.annotations.SerializedName
import io.elixxir.xxclient.utils.RoundId

data class SendReport(
    @SerializedName("Rounds")
    val roundIdList: List<RoundId>?,
    @SerializedName("MessageID")
    val messageId: String?,
    @SerializedName("Timestamp")
    val timestamp: Long?,
    @SerializedName("RoundURL")
    val roundUrl: String?,
    @SerializedName("KeyResidue")
    val keyResidue: String?
) : BindingsModel