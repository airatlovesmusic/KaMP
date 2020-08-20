package com.airatlovesmusic.common_client

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val ApplicationDispatcher: CoroutineDispatcher
    get() = Dispatchers.Default
internal actual val UIDispatcher: CoroutineDispatcher
    get() = Dispatchers.Main