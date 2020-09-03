package com.airatlovesmusic.shared.data.network

import com.airatlovesmusic.shared.Constants
import com.airatlovesmusic.shared.entity.Article
import io.ktor.client.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

// TODO add switch base url
const val currentBaseUrl = Constants.BaseUrl.LOCAL

class ApiClient {

    private val client: HttpClient by lazy {
        HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    suspend fun getArticles(): List<Article> =
        client.get<String>("$currentBaseUrl/articles").let {
            Json.decodeFromString(ListSerializer(Article.serializer()), it)
        }

    suspend fun getArticle(url: String): Article =
        client.get<String>("$currentBaseUrl/article/$url").let {
            Json.decodeFromString(Article.serializer(), it)
        }

}