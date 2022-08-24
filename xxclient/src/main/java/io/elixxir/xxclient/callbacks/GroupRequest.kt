package io.elixxir.xxclient.callbacks

import io.elixxir.xxclient.group.Group
import io.elixxir.xxclient.group.GroupAdapter
import bindings.Group as GroupBindings
import bindings.GroupRequest as GroupRequestBindings

interface GroupRequestListener {
    fun onRequestReceived(group: Group)
}

open class GroupRequestAdapter(
    protected val listener: GroupRequestListener
) : GroupRequestBindings {
    override fun callback(group: GroupBindings?) {
        group?.let {
            listener.onRequestReceived(GroupAdapter(it))
        }
    }
}