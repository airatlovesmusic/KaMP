package com.airatlovesmusic.model

import kotlinx.serialization.*

@Serializable
data class Article(
    val id: Int,
    val title: String,
    val imageUrl: String
)