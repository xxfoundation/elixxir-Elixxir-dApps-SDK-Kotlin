package io.elixxir.xxclient.filetransfer

import bindings.FileTransfer

interface FileTransfer {

}

open class FileTransferAdapter(protected val binding: FileTransfer) : io.elixxir.xxclient.filetransfer.FileTransfer