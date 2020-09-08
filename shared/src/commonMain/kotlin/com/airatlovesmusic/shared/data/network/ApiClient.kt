package com.airatlovesmusic.shared.data.network

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.Constants
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

// TODO add switch base url
const val currentBaseUrl = Constants.BaseUrl.GITHUB

expect val httpClientEngine: HttpClientEngine

class ApiClient {

    private val client: HttpClient by lazy {
        HttpClient(httpClientEngine) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    suspend fun getArticles(): List<Article> =
        client.get<String>("$currentBaseUrl/multik/master/articles.json").let {
            Json.decodeFromString(ListSerializer(Article.serializer()), it)
        }

}