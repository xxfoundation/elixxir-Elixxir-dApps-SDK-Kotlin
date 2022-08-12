package io.elixxir.dapp.android.model

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher

/**
 * Android-specific properties used at runtime.
 */
interface AndroidConfig {
    val context: () -> Context
    val dispatcher: CoroutineDispatcher
}