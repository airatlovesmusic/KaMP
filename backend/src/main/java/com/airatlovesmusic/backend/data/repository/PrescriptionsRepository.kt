package com.airatlovesmusic.backend.data.repository

import com.airatlovesmusic.backend.data.Database.dbQuery
import com.airatlovesmusic.backend.db.tables.Prescriptions
import com.airatlovesmusic.backend.entity.CreatePrescription
import com.airatlovesmusic.backend.entity.Prescription
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import java.util.*

class PrescriptionsRepository {

    suspend fun getPrescriptionsByUserId(userId: Int) = dbQuery {
        Prescriptions.select { Prescriptions.userId eq userId }
            .map { toPrescription(it) }
    }

    suspend fun createPrescription(request: CreatePrescription, userId: Int): Prescription? = dbQuery {
        val createdAt = Date().time
        Prescriptions.insert {
            it[Prescriptions.userId] = userId
            // TODO add proper (de)serialization
            it[schedule] = request.schedule.joinToString(separator = "|") { "${it.first}-${it.second}" }
            it[name] = request.name
            it[Prescriptions.createdAt] = createdAt
        }
        Prescriptions.select { Prescriptions.createdAt eq createdAt }
            .map { toPrescription(it) }
            .singleOrNull()
    }

    private fun toPrescription(row: ResultRow) =
        Prescription(
            id = row[Prescriptions.id],
            name = row[Prescriptions.name],
            createdAt = row[Prescriptions.createdAt],
            // TODO add proper (de)serialization
            schedule = row[Prescriptions.schedule].split("|").map {
                val startEnd = it.split("-").map { it.toIntOrNull() }
                startEnd.firstOrNull() to startEnd.getOrNull(1)
            }
        )

}