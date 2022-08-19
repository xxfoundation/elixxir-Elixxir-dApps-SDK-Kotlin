package io.elixxir.dapp.bindings.model

internal interface UdListener {
    val onComplete: (Contact?, Exception?) -> Unit
}

internal class UdSearchResultListener(
    private val listener: UdListener
) : bindings.UdSearchCallback,
    bindings.UdLookupCallback,
    UdListener by listener
{

    override fun callback(
        contactData: ByteArray?,
        error: java.lang.Exception?
    ) {
        onComplete(
            Contact(contactData ?: byteArrayOf()),
            error
        )
    }

    companion object {
        val placeholder: UdSearchResultListener =
            UdSearchResultListener(
                object : UdListener {
                    override val onComplete: (Contact?, Exception?) -> Unit
                            = { _, _, -> }
                }
            )
    }
}