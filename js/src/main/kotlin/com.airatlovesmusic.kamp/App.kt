package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp

import react.RBuilder
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch

fun RBuilder.app() =
    browserRouter {
        switch {
            route("/", exact = true) {
                child(Articles::class) {}
            }
            route<Article.ArticleProps>("/article/:id") { props ->
                article(props.match.params.id)
            }
        }
    }