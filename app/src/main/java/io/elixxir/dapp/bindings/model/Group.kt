package io.elixxir.dapp.bindings.model

import io.elixxir.dapp.group.model.GroupId

internal interface Group {
    val id: GroupId
    val name: String
    val initMessage: String
    val members: List<String>
    val creationTimeMs: Long
}