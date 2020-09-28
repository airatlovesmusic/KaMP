package com.airatlovesmusic.shared.data.network

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.Constants
import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.coroutinesinterop.completableFromCoroutine
import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class NetworkSourceImpl: NetworkSource {

    private val httpClient: HttpClient = HttpClient()

    override fun getArticles(): Single<List<Article>> =
        singleFromCoroutine {
            httpClient.get<String>("${Constants.BaseUrl.PROD}/${ARTICLES_ENDPOINT}")
                .let { Json.decodeFromString(ListSerializer(Article.serializer()), it) }
        }

    override fun getArticle(id: String): Single<Article> =
        singleFromCoroutine {
            httpClient.get<String>("${Constants.BaseUrl.PROD}/${ARTICLE_ENDPOINT}?id=$id")
                .let { Json.decodeFromString(Article.serializer(), it) }
        }

    override fun login(username: String, password: String): Single<String> =
        singleFromCoroutine {
            httpClient.post("${Constants.BaseUrl.PROD}/${ARTICLE_ENDPOINT}") {
                body = LoginRegisterRequest(username, password)
            }
        }

    override fun register(username: String, password: String): Single<String> =
        singleFromCoroutine {
            httpClient.post("${Constants.BaseUrl.PROD}/${REGISTER_ENDPOINT}") {
                body = LoginRegisterRequest(username, password)
            }
        }

    companion object {
        private const val ARTICLES_ENDPOINT = "articles"
        private const val ARTICLE_ENDPOINT = "article"

        private const val LOGIN_ENDPOINT = "login"
        private const val REGISTER_ENDPOINT = "register"
    }
}

// TODO move to model module
data class LoginRegisterRequest(val username: String, val password: String)