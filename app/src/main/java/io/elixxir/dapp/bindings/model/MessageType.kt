package io.elixxir.dapp.bindings.model

internal enum class MessageType(val code: Long) {
    NoType(0),
    XxMessage(2),
    KeyExchangeTrigger(30),
    KeyExchangeConfirm(31),
    KeyExchangeTriggerEphemeral(32),
    KeyExchangeConfirmEphemeral(33),
    E2eClose(34),
    GroupCreationRequest(40),
    NewFileTransfer(50),
    EndFileTransfer(51),
    ConnectionAuthenticationRequest(60);

    companion object {
        fun from(code: Long): MessageType {
            return values().firstOrNull {
                it.code == code
            } ?: throw(IllegalArgumentException("Invalid code"))
        }
    }
}