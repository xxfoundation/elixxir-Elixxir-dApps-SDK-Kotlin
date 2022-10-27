package io.elixxir.xxclient.models


data class GroupMember(
    val id: ByteArray,
    val dhKey: DHKey
) : BindingsModel

data class DHKey(
    val value: String,
    val fingerprint: Int
) : BindingsModel