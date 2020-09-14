package com.airatlovesmusic.shared.data.network

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.Constants
import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.onErrorReturn
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

internal class NetworkSourceImpl: NetworkSource {

    private val httpClient = HttpClient(Js)

    override fun getArticles(): Single<List<Article>> {
        return singleFromCoroutine { fetchArticles() }
            .onErrorReturn { listOf() }
    }

    private suspend fun fetchArticles(): List<Article> =
        httpClient.get<String>("${Constants.BaseUrl.GITHUB}/${NetworkConstants.ARTICLES_ENDPOINT}").let {
            Json.decodeFromString(ListSerializer(Article.serializer()), it)
        }
}

internal actual fun networkSource(): NetworkSource = NetworkSourceImpl()