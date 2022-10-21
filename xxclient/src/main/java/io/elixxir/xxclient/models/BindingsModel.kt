package io.elixxir.xxclient.models

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface BindingsModel {

    companion object {
        fun encode(model: BindingsModel): ByteArray {
            return Gson().toJson(model).encodeToByteArray()
        }

        inline fun <reified T> encodeArray(list: List<T>): ByteArray {
            val typeToken = object : TypeToken<Array<T>>() {}.type
            return Gson().toJson(list.toTypedArray(), typeToken).encodeToByteArray()
        }

        inline fun <reified T> decode(data: ByteArray): T {
            val typeToken = object : TypeToken<T>() {}.type
            return Gson().fromJson(data.decodeToString(), typeToken)
        }

        inline fun <reified T> decodeArray(data: ByteArray): List<T> {
            val typeToken = object : TypeToken<Array<T>>() {}.type
            return Gson().fromJson<Array<T>>(data.decodeToString(), typeToken).toList()
        }
    }
}