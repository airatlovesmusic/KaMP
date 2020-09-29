package com.airatlovesmusic.model.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRegisterRequest(
    val username: String,
    val password: String
)