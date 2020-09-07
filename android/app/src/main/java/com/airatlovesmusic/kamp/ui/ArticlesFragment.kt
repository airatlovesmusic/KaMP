package com.airatlovesmusic.kamp.ui

import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.shared.base.BaseFragment
import com.airatlovesmusic.shared.presentation.ArticlesFeature
import com.airatlovesmusic.shared.presentation.News
import com.airatlovesmusic.shared.presentation.State
import org.koin.core.get

class ArticlesFragment: BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.activity_main

    private val feature = ArticlesFeature(get(), ::renderState, ::handleNews)

    override fun onDestroy() {
        super.onDestroy()
        feature.dispose()
    }

    private fun handleNews(news: News) {
        println("news - $news")
    }

    private fun renderState(state: State) {
        println("state - $state")
    }

}