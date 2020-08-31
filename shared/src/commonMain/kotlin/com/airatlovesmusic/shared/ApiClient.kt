package com.airatlovesmusic.shared

expect class ApiClient {
    fun getArticles(): List<String>
}