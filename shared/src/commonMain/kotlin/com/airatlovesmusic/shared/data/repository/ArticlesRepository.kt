package com.airatlovesmusic.shared.data.repository

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.data.network.NetworkSource
import com.badoo.reaktive.single.*

class ArticlesRepository(private val networkSource: NetworkSource) {

    fun getArticles(): Single<List<Article>> =
        networkSource.getArticles()

    fun getArticle(id: String): Single<Article> =
        networkSource.getArticle(id)
}