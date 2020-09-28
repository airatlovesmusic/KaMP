package com.airatlovesmusic.backend

import com.airatlovesmusic.model.Article
import io.ktor.application.call
import io.ktor.http.*
import io.ktor.response.respond
import io.ktor.routing.*

fun Routing.article() {
    route("/articles", HttpMethod.Get) {
        get { call.respond(getArticles()) }
    }
    route("/article") {
        get {
            call.request.queryParameters["id"]?.let { id ->
                getArticles().firstOrNull { it.id == id.toIntOrNull() }?.let {
                    call.respond(it)
                } ?: call.respond(HttpStatusCode.NotFound)
            } ?: call.respond(HttpStatusCode.BadRequest)
        }
    }
}

private fun getArticles() = (0..10).map {
    Article(
        id = it,
        title = "Article #$it",
        url = "https://i.pinimg.com/736x/3c/9d/b8/3c9db857044dfc08176aa90d242799f5.jpg"
    )
}