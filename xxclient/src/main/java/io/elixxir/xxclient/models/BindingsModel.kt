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
            return Gson().toJson(data.toTypedArray(), Array<T>::class.java).encodeToByteArray()
        }

        inline fun <reified T> decode(data: ByteArray?): T? {
            return data?.run {
                if (isValidData()) {
                    val typeToken = object : TypeToken<T>() {}.type
                    Gson().fromJson<T>(decodeToString(), typeToken)
                } else {
                    Log.d("Decode", "Failed to decode data: ${decodeToString()}")
                    null
                }
            }
        }

        inline fun <reified T> decodeArray(data: ByteArray?): List<T> {
            return data?.run {
                if (isValidData()) {
                    val typeToken = object : TypeToken<Array<T>>() {}.type
                    Gson().fromJson<Array<T>>(decodeToString(), typeToken).toList()
                } else listOf()
            } ?: listOf()
        }

        fun ByteArray.isValidData(): Boolean {
            return isNotEmpty() && decodeToString() != "[]" && decodeToString() != "null"
        }
    }
}