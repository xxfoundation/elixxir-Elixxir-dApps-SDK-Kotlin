package io.elixxir.xxclient.callbacks

import io.elixxir.xxclient.filetransfer.FilePartTracker
import io.elixxir.xxclient.models.Progress

interface FileTransferProgressListener {
    fun onProgressUpdate(progress: Result<FileTransferProgress>)
}

data class FileTransferProgress(
    val progress: Progress,
    val partTracker: FilePartTracker
)