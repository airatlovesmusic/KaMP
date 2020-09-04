package com.airatlovesmusic.shared

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

internal actual val ApplicationDispatcher: CoroutineContext
    get() = Dispatchers.Main