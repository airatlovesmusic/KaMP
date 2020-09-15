package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.presentation.ArticlesFeatureComponent
import kotlinx.html.DIV
import react.*
import react.dom.*
import react.router.dom.navLink

class Articles : RComponent<RProps, Articles.ArticlesState>() {

    private val feature: ArticlesFeatureComponent

    init {
        state = ArticlesState()
        feature = ArticlesFeatureComponent()
        feature.bindListeners(
            stateListener = { state ->
                setState {
                    isLoading = state.isLoading
                    list = state.articles
                }
            },
            newsListener = {}
        )
    }

    private fun RDOMBuilder<DIV>.renderProgress() {
        if (state.isLoading) {
            progress {}
        }
    }

    private fun RDOMBuilder<DIV>.renderList() {
        state.list?.let {
            span {
                it.forEach { article ->
                    navLink(to = "/article/${article.url}") {
                        h2(classes = "title") {
                            +article.title
                        }
                    }
                }
            }
        }
    }

    override fun RBuilder.render() {
        div(classes = "container") {
            renderProgress()
            renderList()
        }
    }

    data class ArticlesState(
        var list: List<Article>? = null,
        var isLoading: Boolean = false
    ): RState
}
