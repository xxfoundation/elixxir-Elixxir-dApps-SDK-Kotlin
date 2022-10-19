package io.elixxir.xxclient.models

class ReceptionIdentity(
    val id: String,
    val rsaPrivatePem: String,
    val salt: String,
    val dhKeyPrivate: String,
    val e2eGrp: String
): BindingsModel