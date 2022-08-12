package io.elixxir.dapp.session

interface SessionConfig {
    val ndfConfig: NdfConfig

    interface NdfConfig {
        val ndfUrl: String
        val ndfMaxRetries: Int
        val ndfRetryInterval: Long
    }
}