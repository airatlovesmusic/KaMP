package com.airatlovesmusic.shared.data.repository

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.data.network.NetworkSource
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.map

class ArticlesRepository(private val networkSource: NetworkSource) {

    fun getArticles(): Single<List<Article>> =
        networkSource.getArticles()

    fun getArticle(url: String): Single<Article?> =
        getArticles().map { it.firstOrNull { it.url == url } }
}