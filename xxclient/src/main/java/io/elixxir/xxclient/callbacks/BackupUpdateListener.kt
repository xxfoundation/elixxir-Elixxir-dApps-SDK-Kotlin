package io.elixxir.xxclient.callbacks

import bindings.UpdateBackupFunc
import io.elixxir.xxclient.utils.BackupData

interface BackupUpdateListener {
    fun onUpdate(encryptedBackup: BackupData)
}

open class UpdateBackupFuncAdapter(
    protected val listener: BackupUpdateListener
) : UpdateBackupFunc {
    override fun updateBackup(encrpytedBackup: BackupData?) {
        encrpytedBackup?.let {
            listener.onUpdate(it)
        }
    }
}