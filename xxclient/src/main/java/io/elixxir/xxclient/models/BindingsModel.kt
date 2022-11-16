package io.elixxir.xxclient.models

import android.util.Log
import com.google.gson.Gson
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
            return Gson().toJson(data.toTypedArray(), T::class.java).encodeToByteArray()
        }

        inline fun <reified T> decode(data: ByteArray?): T? {
            return data?.run {
                if (isValidData()) {
                    Gson().fromJson(decodeToString(), T::class.java)
                } else {
                    Log.d("Decode", "Failed to decode data: ${decodeToString()}")
                    null
                }
            }
        }

        inline fun <reified T> decodeArray(data: ByteArray?): List<T> {
            return data?.run {
                if (isValidData()) {
                    Gson().fromJson(decodeToString(), Array<T>::class.java).toList()
                } else listOf()
            } ?: listOf()
        }

        fun ByteArray.isValidData(): Boolean {
            return isNotEmpty() && decodeToString() != "[]" && decodeToString() != "null"
        }
    }
}