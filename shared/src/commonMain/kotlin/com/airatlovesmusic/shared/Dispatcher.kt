package com.airatlovesmusic.shared

import kotlinx.coroutines.CoroutineDispatcher

internal expect val ApplicationDispatcher: CoroutineDispatcher
internal expect val MainDispatcher: CoroutineDispatcher