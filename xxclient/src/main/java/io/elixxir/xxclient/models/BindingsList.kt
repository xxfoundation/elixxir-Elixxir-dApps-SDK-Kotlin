package io.elixxir.xxclient.models

import io.elixxir.xxclient.models.BindingsModel.Companion.encode

interface BindingsList<out E> : List<E>, BindingsModel

inline fun <reified E> BindingsList<E>.encoded(): ByteArray {
    return encode(this)
}

class BindingsListAdapter<T>(
    list: List<T>
): List<T> by list, BindingsModel, BindingsList<T>