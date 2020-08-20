package com.airatlovesmusic.backend

import com.google.gson.GsonBuilder
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.gson.GsonConverter
import io.ktor.http.ContentType
import io.ktor.routing.Routing

fun Application.module() {
    install(ContentNegotiation) {
        register(
            ContentType.Application.Json,
            GsonConverter(
                GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .create()
            )
        )
    }
    install(CORS)
    install(Routing) {
        article()
    }
}