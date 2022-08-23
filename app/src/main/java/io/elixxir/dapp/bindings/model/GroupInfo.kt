package io.elixxir.dapp.bindings.model

internal data class GroupInfo(
    val membershipData: GroupMembership,
    val description: String,
    val name: String
)