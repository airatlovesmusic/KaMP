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
        header("sec-ch-ua")
        header("sec-ch-ua-mobile")
        header("Sec-Fetch-Dest")
        header("Sec-Fetch-Mode")
        header("Sec-Fetch-Site")
    }
    install(Routing) {
        article()
    }
}