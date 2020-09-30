package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp

import com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components.Article
import com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components.Articles
import com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components.article
import com.airatlovesmusic.shared.data.repository.AuthRepository
import org.koin.core.Koin
import react.RBuilder
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch

fun RBuilder.app(koin: Koin) =
    browserRouter {
        val isAuthorized = koin.get<AuthRepository>().isAuthorized()
        switch {
            route("/", exact = isAuthorized) {
                child(Articles::class) {}
            }
            route<Article.ArticleProps>("/article/:id", exact = !isAuthorized) { props ->
                article(props.match.params.id)
            }
        }
    }
