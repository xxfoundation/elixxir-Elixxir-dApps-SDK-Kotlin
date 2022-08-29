package io.elixxir.xxclient.utils

import io.elixxir.xxclient.models.BindingsModel
import io.elixxir.xxclient.models.BindingsModel.Companion.encode
import io.elixxir.xxclient.models.Contact
import io.elixxir.xxclient.models.ContactAdapter
import io.elixxir.xxclient.models.InvalidDataException

/**
 * Returns a [Result] from a Bindings callback using the [data], [error],
 * and desired [model] class.
 */
inline fun <reified T> parse(data: ByteArray?, error: Exception?, model: Class<T>) : Result<T> {
    return error?.let {
        Result.failure(it)
    } ?: data?.let {
        Result.success(
            BindingsModel.decode(it, model)
        )
    } ?: Result.failure(InvalidDataException())
}

fun Contact.encoded(): ByteArray {
    return encode(ContactAdapter(data))
}