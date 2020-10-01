package com.airatlovesmusic.backend.data

import com.airatlovesmusic.backend.db.tables.Articles
import com.airatlovesmusic.backend.db.tables.Prescriptions
import com.airatlovesmusic.backend.db.tables.Users
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt
import java.util.*

object Database {

    fun init(){
        Database.connect(hikari())
        transaction {
            create(Articles, Users)
            // Fill the database
            (0..10).map { index ->
                Articles.insert {
                    it[id] = index
                    it[title] = "Title #$index"
                }
            }
            Users.insert {
                it[username] = "admin"
                it[password] = BCrypt.hashpw("admin", BCrypt.gensalt())
            }
            Users.selectAll().toList().forEach {
                val userId = it[Users.id]
                Prescriptions.insert {
                    it[Prescriptions.userId] = userId
                    it[name] = "test"
                    it[createdAt] = Date().time
                    it[schedule] = ""
                }
            }
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction { block() }
        }

}