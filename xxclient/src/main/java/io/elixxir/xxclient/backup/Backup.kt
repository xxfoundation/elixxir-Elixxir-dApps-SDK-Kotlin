package io.elixxir.xxclient.backup

interface Backup {
    fun isBackupRunning(): Boolean
    fun addJson(json: String)
    fun stopBackup()
}

open class BackupAdapter(protected val backup: bindings.Backup) : Backup {
    override fun isBackupRunning(): Boolean = backup.isBackupRunning

    override fun addJson(json: String) = backup.addJson(json)

    override fun stopBackup() = backup.stopBackup()
}