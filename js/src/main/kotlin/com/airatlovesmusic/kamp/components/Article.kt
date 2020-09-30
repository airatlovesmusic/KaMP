package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components

import com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components.base.BaseComponent
import com.airatlovesmusic.shared.presentation.ArticleFeatureComponent
import com.airatlovesmusic.shared.router.Router
import react.*
import react.dom.div
import react.dom.h6
import react.dom.span
import com.airatlovesmusic.model.Article as ArticleModel

class Article(
    id: String,
    parentRouter: Router
): BaseComponent<Article.ArticleProps, Article.ArticleState>(
    props = object : ArticleProps {
        override var id: Int = id.toIntOrNull() ?: 0
    },
    parentRouter = parentRouter
) {

    private lateinit var feature: ArticleFeatureComponent

    init {
        state = ArticleState()
    }

    override fun componentDidMount() {
        feature = ArticleFeatureComponent(props.id.toString(), parentRouter)
        feature.bindListeners(
            stateListener = {
                setState {
                    isLoading = it.isLoading
                    article = it.article
                }
            },
            newsListener = { news ->
                when (news) {
                    is ArticleFeatureComponent.News.GetArticleFailure -> showError(
                        news.throwable.message ?: "Unknown"
                    )
                }
            }
        )
    }

    override fun RBuilder.render() {
        div("container") {
            renderTitle()
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
        var article: ArticleModel? = null
    ): RState

}

fun RBuilder.article(id: Int) = child(Article::class) {
    attrs.id = id
}
