package io.elixxir.xxclient.filetransfer

import io.elixxir.xxclient.models.FilePartStatus
import bindings.FilePartTracker as FilePartTrackerBindings

interface FilePartTracker {
    val numParts: Long
    fun getPartStatus(partNum: Long): FilePartStatus
}

open class FilePartTrackerAdapter(
    protected val bindings: FilePartTrackerBindings
) : FilePartTracker {
    override val numParts: Long
        get() = bindings.numParts

    override fun getPartStatus(partNum: Long): FilePartStatus {
        return FilePartStatus.from(
            bindings.getPartStatus(partNum)
        )
    }
}