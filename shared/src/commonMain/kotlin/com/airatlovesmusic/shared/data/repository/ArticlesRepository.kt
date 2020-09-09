package com.airatlovesmusic.shared.data.repository

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.data.network.NetworkSource
import com.badoo.reaktive.single.*

class ArticlesRepository(private val networkSource: NetworkSource) {

    fun getArticles(): Single<List<Article>> =
        networkSource.getArticles()

    fun getArticle(url: String): Single<Article> =
        getArticles().flatMap { it.firstOrNull { it.url == url }?.toSingle() ?: singleOfError(Exception("No article")) }
}