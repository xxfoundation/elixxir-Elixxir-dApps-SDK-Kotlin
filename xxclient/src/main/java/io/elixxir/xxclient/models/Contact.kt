package io.elixxir.xxclient.models

import bindings.Bindings
import io.elixxir.xxclient.models.BindingsModel.Companion.decode
import io.elixxir.xxclient.models.BindingsModel.Companion.encode
import io.elixxir.xxclient.utils.ContactData


interface Contact {
    val data: ContactData
    fun getIdFromContact(contactData: ContactData): ByteArray
    fun getPublicKeyFromContact(contactData: ContactData): ByteArray
    fun getFactsFromContact(contactData: ContactData): Fact
    fun setFactsOnContact(contactData: ContactData, fact: Fact): ContactData
}

data class ContactAdapter(
    override val data: ContactData,
) : BindingsModel, Contact {

    override fun getIdFromContact(contactData: ContactData): ByteArray {
        return Bindings.getIDFromContact(contactData)
    }

    override fun getPublicKeyFromContact(contactData: ContactData): ByteArray {
        return Bindings.getPubkeyFromContact(contactData)
    }

    override fun getFactsFromContact(contactData: ContactData): Fact {
        return decode(Bindings.getFactsFromContact(contactData), Fact::class.java)
    }

    override fun setFactsOnContact(contactData: ContactData, fact: Fact): ContactData {
        return Bindings.setFactsOnContact(contactData, encode(fact))
    }
}