package com.airatlovesmusic.shared.data.network

import com.airatlovesmusic.model.Article
import com.badoo.reaktive.single.Single

internal expect fun networkSource(): NetworkSource

interface NetworkSource {
    fun getArticles(): Single<List<Article>>
}

object NetworkConstants {
    const val ARTICLES_ENDPOINT = "multik/master/articles.json"
}