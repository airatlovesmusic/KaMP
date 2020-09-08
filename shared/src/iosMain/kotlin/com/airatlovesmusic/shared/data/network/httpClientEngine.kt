package com.airatlovesmusic.shared.data.network

import io.ktor.client.engine.*
import io.ktor.client.engine.ios.*

actual val httpClientEngine: HttpClientEngine
    get() = Ios.create()