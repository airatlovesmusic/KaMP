package com.airatlovesmusic.backend.entity

data class Prescription(
    val id: Int,
    val name: String,
    val createdAt: Long,
    val schedule: List<Pair<Int?, Int?>>
)