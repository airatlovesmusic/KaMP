package com.airatlovesmusic.backend

import io.ktor.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    val port = System.getenv("PORT")?.toInt() ?: 23567
    embeddedServer(
        factory = Netty,
        port = port,
        module = Application::module
    ).start(wait = true)
}