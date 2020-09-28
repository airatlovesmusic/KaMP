package com.airatlovesmusic.backend.data

import com.airatlovesmusic.backend.Database.dbQuery
import com.airatlovesmusic.backend.db.Articles
import com.airatlovesmusic.model.Article
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class ArticlesRepository {

    suspend fun getArticles(): List<Article> = dbQuery {
        Articles.selectAll().map { toArticle(it) }
    }

    suspend fun getArticle(id: Int): Article? = dbQuery {
        Articles.select { Articles.id eq id }
            .mapNotNull { toArticle(it) }
            .singleOrNull()
    }

    private fun toArticle(row: ResultRow) = Article(
        id = row[Articles.id],
        title = row[Articles.title],
        url = "Url"
    )

}