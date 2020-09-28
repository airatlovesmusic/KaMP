package com.airatlovesmusic.backend.db

import org.jetbrains.exposed.sql.Table

object Articles: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = varchar("title", 100)
}