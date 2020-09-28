package com.airatlovesmusic.kamp

import androidx.fragment.app.Fragment
import com.airatlovesmusic.kamp.ui.article.ArticleFragment
import com.airatlovesmusic.kamp.ui.articles.ArticlesFragment
import com.airatlovesmusic.kamp.ui.auth.AuthFlowFragment
import com.airatlovesmusic.kamp.ui.auth.LoginFragment
import com.airatlovesmusic.shared.router.Screen
import com.airatlovesmusic.shared.router.Screens

class ScreensImpl: Screens {
    override fun articles() = object : Screen() {
        override fun getFragment() = ArticlesFragment()
    }
    override fun article(id: String) = object: Screen() {
        override fun getFragment() = ArticleFragment.create(id)
    }
    fun authFlow() = object : Screen() {
        override fun getFragment() = AuthFlowFragment()
    }
    override fun login() = object : Screen() {
        override fun getFragment() = LoginFragment()
    }
}