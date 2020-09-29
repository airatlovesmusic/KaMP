package com.airatlovesmusic.shared.data.network

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.Constants
import com.airatlovesmusic.shared.data.preferences.Preferences
import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import org.koin.core.KoinComponent

class NetworkSourceImpl(
    preferences: Preferences
): NetworkSource, KoinComponent {

    private val httpClient: HttpClient = HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
        defaultRequest {
            header("Authorization", "Bearer " + preferences.getString(Constants.PreferencesKeys.KEY_TOKEN))
        }
    }

    override fun getArticles(): Single<List<Article>> =
        singleFromCoroutine {
            httpClient.get("${Constants.BaseUrl.PROD}/${ARTICLES_ENDPOINT}")
        }

    override fun getArticle(id: String): Single<Article> =
        singleFromCoroutine {
            httpClient.get("${Constants.BaseUrl.PROD}/${ARTICLE_ENDPOINT}?id=$id")
        }

    override fun login(username: String, password: String): Single<String> =
        singleFromCoroutine {
            httpClient.post("${Constants.BaseUrl.PROD}/${LOGIN_ENDPOINT}") {
                contentType(ContentType.Application.Json)
                body = LoginRegisterRequest(username, password)
            }
        }

    override fun register(username: String, password: String): Single<String> =
        singleFromCoroutine {
            httpClient.post("${Constants.BaseUrl.PROD}/${REGISTER_ENDPOINT}") {
                contentType(ContentType.Application.Json)
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
@Serializable
data class LoginRegisterRequest(val username: String, val password: String)