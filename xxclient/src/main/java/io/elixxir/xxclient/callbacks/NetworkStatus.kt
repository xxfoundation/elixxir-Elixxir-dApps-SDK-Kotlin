package io.elixxir.xxclient.callbacks

import io.elixxir.xxclient.models.NetworkFollowerStatus

interface NetworkStatus {
    fun getNetworkStatus(): NetworkFollowerStatus
}