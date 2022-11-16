package io.elixxir.xxclient.models

import com.google.gson.annotations.SerializedName

data class GroupMembersList(
    val members: List<GroupMember>
) : BindingsModel

data class GroupMember(
    @SerializedName("ID")
    val id: ByteArray,
    @SerializedName("DHKey")
    val dhKey: DHKey
) : BindingsModel {

    data class DHKey(
        @SerializedName("Value")
        val value: String,
        @SerializedName("Fingerprint")
        val fingerprint: Int
    ) : BindingsModel
}