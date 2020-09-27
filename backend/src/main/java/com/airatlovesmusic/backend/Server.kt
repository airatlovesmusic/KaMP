package com.airatlovesmusic.backend

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.routing.*

fun Application.module() {
    install(Routing) {
        article()
    }
}