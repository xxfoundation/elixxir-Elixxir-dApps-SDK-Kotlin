package io.elixxir.xxclient.models

import io.elixxir.xxclient.utils.FileTransferId
import io.elixxir.xxclient.utils.UserId

data class ReceivedFile(
    val transferId: FileTransferId,
    val senderId: UserId,
    val previewData: ByteArray,
    val fileName: String,
    val filetype: String,
    val fileSize: Long
) : BindingsModel