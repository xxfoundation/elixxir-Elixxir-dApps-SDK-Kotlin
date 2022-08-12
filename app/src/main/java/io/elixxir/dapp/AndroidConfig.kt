package io.elixxir.dapp

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher

interface AndroidConfig {
    val defaultDispatcher: CoroutineDispatcher
    val context: () -> Context
}