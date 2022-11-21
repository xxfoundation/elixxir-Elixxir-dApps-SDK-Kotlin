package io.elixxir.xxclient.models

import com.google.gson.annotations.SerializedName

data class GroupReport(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Rounds")
    val rounds: List<Long>,
    @SerializedName("RoundURL")
    val roundUrl: String,
    @SerializedName("Status")
    val status: Long
) : BindingsModel