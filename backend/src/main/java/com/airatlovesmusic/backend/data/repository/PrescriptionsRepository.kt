package com.airatlovesmusic.backend.data.repository

import com.airatlovesmusic.backend.data.Database.dbQuery
import com.airatlovesmusic.backend.db.tables.Prescriptions
import com.airatlovesmusic.backend.entity.Prescription
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select

class PrescriptionsRepository {

    suspend fun getPrescriptionsByUserId(userId: Int) = dbQuery {
        Prescriptions.select { Prescriptions.userId eq userId }
            .map { toPrescription(it) }
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