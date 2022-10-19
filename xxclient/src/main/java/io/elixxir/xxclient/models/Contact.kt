package io.elixxir.xxclient.models

import bindings.Bindings
import io.elixxir.xxclient.models.BindingsModel.Companion.decodeArray
import io.elixxir.xxclient.models.BindingsModel.Companion.encodeArray
import io.elixxir.xxclient.utils.ContactData


interface Contact {
    val data: ContactData
    fun getIdFromContact(): ByteArray
    fun getPublicKeyFromContact(): ByteArray
    fun getFactsFromContact(): List<Fact>
    fun setFactsOnContact(facts: List<Fact>): Contact
}

data class ContactAdapter(
    override val data: ContactData,
) : Contact {

    override fun getIdFromContact(): ByteArray {
        return Bindings.getIDFromContact(data)
    }

    override fun getPublicKeyFromContact(): ByteArray {
        return Bindings.getPubkeyFromContact(data)
    }

    override fun getFactsFromContact(): List<Fact> {
        return decodeArray(Bindings.getFactsFromContact(data))
    }

    override fun setFactsOnContact(facts: List<Fact>): Contact {
        return ContactAdapter(Bindings.setFactsOnContact(data, encodeArray(facts)))
    }
}