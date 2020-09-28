package com.airatlovesmusic.backend.db.tables

import org.jetbrains.exposed.sql.Table

object Users: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val username = varchar("name", 100)
    val password = varchar("password", 100)
}