package io.elixxir.xxclient.models

import bindings.ReceiveFileCallback
import io.elixxir.xxclient.utils.parseModel
import java.lang.Exception

interface IncomingFileListener {
    fun onFileReceived(content: ReceivedFile)
}

open class ReceiveFileCallbackAdapter(val listener: IncomingFileListener) : ReceiveFileCallback {
    override fun callback(fileData: ByteArray?, error: Exception?) {
        val fileResult: Result<ReceivedFile> = parseModel(fileData, error)
        fileResult.getOrNull()?.let {
            listener.onFileReceived(it)
        }
    }
}