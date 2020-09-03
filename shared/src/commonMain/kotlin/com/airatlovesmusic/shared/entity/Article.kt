package com.airatlovesmusic.shared.entity

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val title: String,
    val url: String
)