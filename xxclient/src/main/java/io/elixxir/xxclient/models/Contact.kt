package io.elixxir.xxclient.models

import bindings.Bindings
import io.elixxir.xxclient.models.BindingsModel.Companion.decodeArray
import io.elixxir.xxclient.models.BindingsModel.Companion.encodeArray
import io.elixxir.xxclient.utils.ContactData


interface Contact {
    val data: ContactData
    fun getIdFromContact(contactData: ContactData): ByteArray
    fun getPublicKeyFromContact(contactData: ContactData): ByteArray
    fun getFactsFromContact(contactData: ContactData): List<Fact>
    fun setFactsOnContact(facts: List<Fact>): ContactData
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

    override fun getFactsFromContact(contactData: ContactData): List<Fact> {
        return decodeArray(Bindings.getFactsFromContact(contactData))
    }

    override fun setFactsOnContact(facts: List<Fact>): ContactData {
        return Bindings.setFactsOnContact(data, encodeArray(facts))
    }
}