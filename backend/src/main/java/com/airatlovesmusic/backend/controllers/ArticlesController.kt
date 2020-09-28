package com.airatlovesmusic.backend.controllers

import com.airatlovesmusic.backend.data.ArticlesRepository
import io.ktor.application.call
import io.ktor.http.*
import io.ktor.response.respond
import io.ktor.routing.*

fun Routing.article() {
    val repository = ArticlesRepository()
    route("/articles", HttpMethod.Get) {
        get {
            val articles = repository.getArticles()
            call.respond(articles)
        }
    }
    route("/article") {
        get {
            call.request.queryParameters["id"]?.toIntOrNull()?.let { id ->
                val article = repository.getArticle(id)
                if (article != null) call.respond(it)
                else call.respond(HttpStatusCode.NotFound)
            } ?: call.respond(HttpStatusCode.BadRequest)
        }
    }
}