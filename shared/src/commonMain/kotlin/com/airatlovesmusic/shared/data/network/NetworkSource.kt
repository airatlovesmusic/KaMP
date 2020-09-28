package com.airatlovesmusic.shared.data.network

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.Constants
import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

interface NetworkSource {
    fun getArticles(): Single<List<Article>>
    fun getArticle(id: Int): Single<Article>
}

class NetworkSourceImpl: NetworkSource {

    private val httpClient: HttpClient = HttpClient()

    override fun getArticles(): Single<List<Article>> =
        singleFromCoroutine {
            httpClient.get<String>("${Constants.BaseUrl.PROD}/${NetworkConstants.ARTICLES_ENDPOINT}")
                .let { Json.decodeFromString(ListSerializer(Article.serializer()), it) }
        }

    override fun getArticle(id: Int): Single<Article> =
        singleFromCoroutine {
            httpClient.get<String>("${Constants.BaseUrl.PROD}/${NetworkConstants.ARTICLE_ENDPOINT}?id=$id")
                .let { Json.decodeFromString(Article.serializer(), it) }
        }
}

object NetworkConstants {
    const val ARTICLES_ENDPOINT = "articles"
    const val ARTICLE_ENDPOINT = "article"
}