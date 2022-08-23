package io.elixxir.xxclient.models

import com.google.gson.Gson

interface BindingsModel {
    fun encode(model: BindingsModel): ByteArray {
        return Gson().toJson(model).toByteArray()
    }

    companion object {
        inline fun <reified T> decode(data: ByteArray, model: Class<T>): T {
            return Gson().fromJson(data.toString(), model)
        }
    }
}