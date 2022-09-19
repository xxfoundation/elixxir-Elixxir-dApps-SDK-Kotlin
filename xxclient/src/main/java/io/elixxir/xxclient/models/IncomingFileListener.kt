package io.elixxir.xxclient.models

import bindings.ReceiveFileCallback
import io.elixxir.xxclient.utils.parse
import java.lang.Exception

interface IncomingFileListener {
    fun onFileReceived(content: ReceivedFile)
}

open class ReceiveFileCallbackAdapter(val listener: IncomingFileListener) : ReceiveFileCallback {
    override fun callback(fileData: ByteArray?, error: Exception?) {
        parse(fileData, error, ReceivedFile::class.java)
    }
}