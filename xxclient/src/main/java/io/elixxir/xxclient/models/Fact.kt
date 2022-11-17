package io.elixxir.xxclient.models

import com.google.gson.annotations.SerializedName

data class Fact(
    @SerializedName("Fact")
    val fact: String,
    @SerializedName("T")
    val type: Int
) : BindingsModel