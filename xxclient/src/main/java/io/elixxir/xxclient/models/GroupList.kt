package io.elixxir.xxclient.models

import io.elixxir.xxclient.group.GroupAdapter

data class GroupList(
    val groupsList: List<GroupAdapter>
) : BindingsModel