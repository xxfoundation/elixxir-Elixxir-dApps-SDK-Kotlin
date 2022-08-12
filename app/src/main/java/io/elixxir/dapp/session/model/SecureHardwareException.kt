package io.elixxir.dapp.session.model

class SecureHardwareException : Exception() {
    override val message: String
        get() = "OS is not hardware-backed and require secure hardware is enabled."
}
