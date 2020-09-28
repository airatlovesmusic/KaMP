package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp

import com.airatlovesmusic.shared.presentation.ArticleFeatureComponent
import react.*
import react.dom.div
import react.dom.h6
import react.dom.span
import com.airatlovesmusic.model.Article as ArticleModel

class Article: RComponent<Article.ArticleProps, Article.ArticleState>() {

    private lateinit var feature: ArticleFeatureComponent

    init {
        state = ArticleState()
    }

    override fun componentDidMount() {
        feature = ArticleFeatureComponent(props.id.toString())
        feature.bindListeners(
            stateListener = {
                setState {
                    isLoading = it.isLoading
                    article = it.article
                    hasFailure = false
                }
            },
            newsListener = {
                when (it) {
                    is ArticleFeatureComponent.News.GetArticleFailure ->
                        setState {
                            hasFailure = true
                        }
                }
            }
        )
    }

    override fun RBuilder.render() {
        div("container") {
            renderTitle()
            error(state.hasFailure)
        }
    }

    private fun RBuilder.renderTitle() {
        state.article?.let { article ->
            span {
                h6(classes = "title") { +article.title }
            }
        }
    }

    interface ArticleProps : RProps {
        var id: Int
    }

    data class ArticleState(
        var isLoading: Boolean = false,
        var article: ArticleModel? = null,
        var hasFailure: Boolean = false
    ): RState

}

fun RBuilder.article(id: Int) = child(Article::class) {
    attrs.id = id
}
