package com.airatlovesmusic.backend

import com.google.gson.GsonBuilder
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.gson.GsonConverter
import io.ktor.http.*
import io.ktor.routing.*

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
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Post)
        method(HttpMethod.Get)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Head)

        header(HttpHeaders.AccessControlAllowHeaders)
        header(HttpHeaders.ContentType)
        header(HttpHeaders.AccessControlAllowOrigin)
        header(HttpHeaders.AccessControlAllowMethods)

        anyHost()
    }
    install(Routing) {
        article()
    }
}