package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp

import com.airatlovesmusic.shared.presentation.ArticleFeatureComponent
import react.*
import react.dom.div
import react.dom.h6
import react.dom.span
import com.airatlovesmusic.model.Article as ArticleModel

class Article: RComponent<Article.ArticleProps, Article.ArticleState>() {

    private val feature: ArticleFeatureComponent

    init {
        state = ArticleState()
        feature = ArticleFeatureComponent(props.url)
        feature.bindListeners(
            stateListener = {
                setState {
                    isLoading = it.isLoading
                    article = it.article
                }
            },
            newsListener = {}
        )
    }

    override fun RBuilder.render() {
        div("container") {
            span {
                h6(classes = "title") { +"Hello World" }
            }
        }
    }

    interface ArticleProps : RProps {
        var url: String
    }

    data class ArticleState(
        var isLoading: Boolean = false,
        var article: ArticleModel? = null
    ): RState

}

fun RBuilder.article(url: String) =
    child(Article::class) {
        attrs.url = url
    }
