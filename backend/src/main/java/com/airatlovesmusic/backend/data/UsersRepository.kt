package com.airatlovesmusic.backend.data

import com.airatlovesmusic.backend.Database.dbQuery
import com.airatlovesmusic.backend.db.Users
import com.airatlovesmusic.backend.entity.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UsersRepository {

    fun findUserById(id: Int): User? = transaction {
        Users
            .select { (Users.id eq id) }
            .mapNotNull { toUser(it) }
            .singleOrNull()
    }

    suspend fun getUserByUsername(username: String): User? = dbQuery {
        Users
            .select { (Users.username eq username) }
            .mapNotNull { toUser(it) }
            .singleOrNull()
    }

    suspend fun createUser(username: String, password: String): User? = dbQuery {
        Users.insert {
            it[Users.username] = username
            it[Users.password] = password
        }
        Users.select { (Users.username eq username) }
            .mapNotNull { toUser(it) }
            .singleOrNull()
    }

    private fun toUser(row: ResultRow) = User(
        id = row[Users.id],
        username = row[Users.username],
        password = row[Users.password]
    )

}