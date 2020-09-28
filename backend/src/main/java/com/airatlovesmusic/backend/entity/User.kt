package com.airatlovesmusic.backend.entity

import io.ktor.auth.*

data class User(
    val id: Int,
    val username: String,
    val password: String
): Principal