package io.elixxir.xxclient.models

import bindings.UpdateBackupFunc

interface BackupUpdateListener {
    fun onBackupUpdated(backupData: ByteArray?)
}

open class UpdateBackupFuncAdapter(protected val listener: BackupUpdateListener) : UpdateBackupFunc {
    override fun updateBackup(backupData: ByteArray?) {
        listener.onBackupUpdated(backupData)
    }
}