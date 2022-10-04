package io.elixxir.xxclient.models

import bindings.UpdateBackupFunc

@Deprecated(
    "Will be removed",
    ReplaceWith("BackupUpdateListener", "io.elixxir.xxclient.callbacks.BackupUpdateListener")
)
interface BackupUpdateListener {
    fun onBackupUpdated(backupData: ByteArray?)
}

@Deprecated(
    "Will be removed",
    ReplaceWith("UpdateBackupFuncAdapter", "io.elixxir.xxclient.callbacks.UpdateBackupFuncAdapter")
)
open class UpdateBackupFuncAdapter(protected val listener: BackupUpdateListener) : UpdateBackupFunc {
    override fun updateBackup(backupData: ByteArray?) {
        listener.onBackupUpdated(backupData)
    }
}