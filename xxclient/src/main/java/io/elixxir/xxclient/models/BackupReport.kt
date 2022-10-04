package io.elixxir.xxclient.models

import bindings.BackupReport as BackupReportBindings

interface BackupReport {
}

open class BackupReportAdapter(
    protected val backupReport: BackupReportBindings
) : BackupReport, BindingsModel