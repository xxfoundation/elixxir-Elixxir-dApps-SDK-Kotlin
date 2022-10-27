package io.elixxir.xxclient.utils

import android.util.Base64.*
import io.elixxir.xxclient.models.*

/**
 * Returns a [Result] from a Bindings callback using the [data] and [error]
 */
inline fun <reified T: BindingsModel> parseModel(data: ByteArray?, error: Exception?): Result<T> {
    return error?.let {
        Result.failure(it)
    } ?: data?.let {
        BindingsModel.decode<T>(it)?.let { model ->
            Result.success(model)
        } ?: Result.failure(NoResultsException())
    } ?: Result.failure(InvalidDataException())
}

fun parseData(rawData: ByteArray?, error: Exception?): Result<ByteArray> {
    return parseDataArray(rawData, error).run {
        getOrNull()?.let {
            Result.success(it.firstOrNull() ?: byteArrayOf())
        } ?: Result.failure(exceptionOrNull() ?: InvalidDataException())
    }
}

fun parseDataArray(dataArray: ByteArray?, error: Exception?): Result<List<ByteArray>> {
    return error?.let {
        Result.failure(it)
    } ?: dataArray?.let {
        if (it.isNotEmpty()) {
            Result.success(
                BindingsModel.decodeArray<String>(it).map { base64Data ->
                    base64Data.fromBase64toByteArray()
                }
            )
        } else Result.success(listOf())
    } ?: Result.failure(InvalidDataException())
}

fun Contact.encoded(): ByteArray {
    return data
}

inline fun <reified T> List<T>.encoded(): ByteArray {
    return BindingsModel.encodeArray(this)
}

inline fun <reified T> nonNullResultOf(block: () -> T?): Result<T> {
    return try {
        block()?.let {
            Result.success(it)
        } ?: throw Exception("Null value returned")
    } catch (e: Exception) {
        Result.failure(e)
    }
}

fun ByteArray.toBase64String(): String {
    return encodeToString(this, NO_WRAP)
}

fun String.fromBase64toByteArray(): ByteArray {
    return decode(this, NO_WRAP)
}