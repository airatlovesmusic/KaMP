package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp

import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.presentation.ArticlesFeatureComponent
import kotlinx.html.DIV
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*

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

    private fun onArticleClicked(event: Event, url: String) {
        event.preventDefault()
        feature.goToArticle(url)
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
                    h2(classes = "title") {
                        +article.title
                        attrs {
                            onClickFunction = {
                                onArticleClicked(it, article.url)
                            }
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
