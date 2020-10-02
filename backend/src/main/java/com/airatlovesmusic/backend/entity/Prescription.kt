package com.airatlovesmusic.backend.entity

import kotlinx.serialization.Serializable

data class Prescription(
    val id: Int,
    val name: String,
    val createdAt: Long,
    val schedule: List<Pair<Int?, Int?>>
)

@Serializable
data class CreatePrescription(
    val name: String,
    val schedule: List<Pair<Int?, Int?>>
)