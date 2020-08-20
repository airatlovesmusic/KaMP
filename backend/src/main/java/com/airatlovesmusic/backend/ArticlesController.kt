package com.airatlovesmusic.backend

import com.airatlovesmusic.model.Article
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.article() {
    route("/articles") {
        get { call.respond(getArticles()) }
    }
}

private fun getArticles() = (0..5).map {
    Article(
        id = it,
        title = "Title $it",
        imageUrl = "https://i.pinimg.com/736x/3c/9d/b8/3c9db857044dfc08176aa90d242799f5.jpg"
    )
}