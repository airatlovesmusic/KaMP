package com.airatlovesmusic.shared

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

internal actual val ApplicationDispatcher: CoroutineDispatcher
    get() = Dispatchers.Default
internal actual val MainDispatcher: CoroutineDispatcher
    get() = Dispatchers.Main