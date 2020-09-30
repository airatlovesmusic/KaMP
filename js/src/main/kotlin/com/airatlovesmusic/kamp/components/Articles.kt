package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components

import com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components.base.BaseComponent
import com.airatlovesmusic.model.Article
import com.airatlovesmusic.shared.presentation.ArticlesFeatureComponent
import com.airatlovesmusic.shared.router.Router
import kotlinx.html.DIV
import react.*
import react.dom.*
import react.router.dom.navLink

class Articles(
    parentRouter: Router
): BaseComponent<RProps, Articles.ArticlesState>(
    props = object : RProps {},
    parentRouter = parentRouter
) {

    private lateinit var feature: ArticlesFeatureComponent

    init {
        state = ArticlesState()
    }

    override fun componentDidMount() {
        feature = ArticlesFeatureComponent(parentRouter)
        feature.bindListeners(
            stateListener = { state ->
                setState {
                    isLoading = state.isLoading
                    list = state.articles
                }
            },
            newsListener = { news ->
                setState {
                    when (news) {
                        is ArticlesFeatureComponent.News.GetArticlesFailure ->
                            showError(news.error)
                    }
                }
            }
        )
    }

    override fun componentWillUnmount() {
        feature.dispose()
    }

    private fun RDOMBuilder<DIV>.renderProgress() {
        if (state.isLoading) {
            progress {}
        }
    }

    private fun RDOMBuilder<DIV>.renderList() {
        state.list?.let {
            it.forEach { article ->
                navLink(to = "/article/${article.id}") {
                    h2(classes = "title") {
                        +article.title
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
        var isLoading: Boolean = false,
    ): RState
}
