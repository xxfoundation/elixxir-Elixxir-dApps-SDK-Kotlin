package io.elixxir.xxclient.models

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.elixxir.xxclient.utils.toBase64String

interface BindingsModel {

    companion object {
        fun encode(model: BindingsModel): ByteArray {
            return Gson().toJson(model).encodeToByteArray()
        }

        inline fun <reified T> encodeArray(list: List<T>): ByteArray {
            val data = list.map {
                (it as? ByteArray)?.toBase64String() ?: it
            }
            return Gson().toJson(
                data.toTypedArray(),
                object : TypeToken<Array<T>>() {}.type
            ).encodeToByteArray()
        }

        inline fun <reified T> decode(data: ByteArray?): T? {
            return data?.run {
                if (isValidData()) {
                    Gson().fromJson<T>(
                        decodeToString(),
                        object : TypeToken<T>() {}.type
                    )
                } else {
                    Log.d("Decode", "Failed to decode data: ${decodeToString()}")
                    null
                }
            }
        }

        inline fun <reified T> decodeArray(data: ByteArray?): List<T> {
            return data?.run {
                if (isValidData()) {
                    Gson().fromJson<Array<T>>(
                        decodeToString(),
                        object : TypeToken<Array<T>>() {}.type
                    ).toList()
                } else listOf()
            } ?: listOf()
        }

        fun ByteArray.isValidData(): Boolean {
            return isNotEmpty() && decodeToString() != "[]" && decodeToString() != "null"
        }
    }
}