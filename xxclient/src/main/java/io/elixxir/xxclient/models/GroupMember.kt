package io.elixxir.xxclient.models

import com.google.gson.annotations.SerializedName
import javax.crypto.interfaces.DHKey

data class GroupMembersList(
    val members: List<GroupMember>
) : BindingsModel

data class GroupMember(
    @SerializedName("ID")
    val id: String,
) : BindingsModel