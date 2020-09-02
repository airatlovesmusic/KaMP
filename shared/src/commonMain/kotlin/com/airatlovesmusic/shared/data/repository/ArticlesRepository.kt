package com.airatlovesmusic.shared.data.repository

import com.airatlovesmusic.shared.data.network.ApiClient

class ArticlesRepository(private val apiClient: ApiClient) {
    suspend fun getArticles() = apiClient.getArticles()
    suspend fun getArticle(url: String) = apiClient.getArticle(url)
}