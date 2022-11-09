package io.elixxir.xxclient.models

import com.google.gson.annotations.SerializedName
import io.elixxir.xxclient.utils.Payload

data class Message(
    @SerializedName("MessageType")
    val messageType: Long,
    @SerializedName("ID")
    val id: String,
    @SerializedName("Payload")
    val payload: String,
    @SerializedName("Sender")
    val sender: String,
    @SerializedName("RecipientID")
    val recipientId: String,
    @SerializedName("EphemeralID")
    val ephemeralId: Long,
    @SerializedName("Timestamp")
    val timestamp: Long,
    @SerializedName("Encrypted")
    val encrypted: Boolean,
    @SerializedName("RoundId")
    val roundId: Long,
    @SerializedName("RoundURL")
    val roundUrl: String?
) : BindingsModel {

    fun getTimestampMs() = timestamp / 1_000_000
}