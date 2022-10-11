package io.elixxir.xxclient.models

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface BindingsModel {

    companion object {
        fun encode(model: BindingsModel): ByteArray {
            return Gson().toJson(model).toByteArray()
        }

        inline fun <reified T> decode(data: ByteArray, model: Class<T>): T {
//            val typeToken = object : TypeToken<T>() {}.type
//            return Gson().fromJson(data.toString(), typeToken)
            return Gson().fromJson(data.toString(), model)
        }

        inline fun <reified T> decodeArray(data: ByteArray): List<T> {
            val typeToken = object : TypeToken<List<T>>() {}.type
            return Gson().fromJson(data.toString(), typeToken)
        }
    }
}