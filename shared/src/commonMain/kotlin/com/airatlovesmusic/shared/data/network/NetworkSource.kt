package com.airatlovesmusic.shared.data.network

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.Constants
import com.badoo.reaktive.completable.Completable
import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

interface NetworkSource {
    fun getArticles(): Single<List<Article>>
    fun getArticle(id: String): Single<Article>
    fun login(username: String, password: String): Single<String>
    fun register(username: String, password: String): Single<String>
}