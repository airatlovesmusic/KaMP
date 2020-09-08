package com.airatlovesmusic.model

import kotlinx.serialization.*

@Serializable
data class Article(
    val title: String,
    val url: String
)