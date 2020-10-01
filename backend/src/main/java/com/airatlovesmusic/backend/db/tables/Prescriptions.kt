package com.airatlovesmusic.backend.db.tables

import org.jetbrains.exposed.sql.Table

object Prescriptions: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val userId = integer("userId") references Users.id
    val name = varchar("name", 100)
    val createdAt = long("createdAt")
    val schedule = varchar("schedule", 200)
}