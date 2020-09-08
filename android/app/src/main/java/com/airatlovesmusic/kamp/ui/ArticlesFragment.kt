package com.airatlovesmusic.kamp.ui

import com.airatlovesmusic.kamp.R
import com.airatlovesmusic.shared.base.BaseFragment
import com.airatlovesmusic.shared.presentation.ArticlesFeatureComponent

class ArticlesFragment: BaseFragment() {

    override val layoutRes: Int
        get() = R.layout.activity_main

    private val featureComponent =
        ArticlesFeatureComponent(
            stateListener = ::renderState,
            newsListener = ::handleNews
        )

    override fun onDestroy() {
        super.onDestroy()
        featureComponent.dispose()
    }

    private fun handleNews(news: ArticlesFeatureComponent.News) {
        println("news - $news")
    }

    private fun renderState(state: ArticlesFeatureComponent.State) {
        println("state - $state")
    }

}