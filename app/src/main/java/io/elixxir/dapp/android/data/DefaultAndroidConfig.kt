package io.elixxir.dapp.android.data

import android.content.Context
import io.elixxir.dapp.android.model.AndroidConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class DefaultAndroidConfig(
    override val context: () -> Context,
    override val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : AndroidConfig