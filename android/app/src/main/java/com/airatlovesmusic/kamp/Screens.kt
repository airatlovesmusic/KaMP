package com.airatlovesmusic.kamp

import com.airatlovesmusic.kamp.ui.article.ArticleFragment
import com.airatlovesmusic.kamp.ui.articles.ArticlesFragment
import com.airatlovesmusic.shared.router.Screen

object Screens {
    object Articles: Screen() {
        override fun getFragment() = ArticlesFragment()
    }
    data class Article(val url: String): Screen() {
        override fun getFragment() = ArticleFragment.create(url)
    }
}