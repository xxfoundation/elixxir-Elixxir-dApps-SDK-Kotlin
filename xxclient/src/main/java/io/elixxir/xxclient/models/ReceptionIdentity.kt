package io.elixxir.xxclient.models

class ReceptionIdentity(
    val id: ByteArray,
    val rsaPrivatePem: ByteArray,
    val salt: ByteArray,
    val dhKeyPrivate: ByteArray,
    val e2eGrp: ByteArray
): BindingsModel