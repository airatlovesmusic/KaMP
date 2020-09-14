package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*

class Articles : RComponent<RProps, Articles.ArticlesState>() {

    init {
        state = ArticlesState()
    }

    override fun RBuilder.render() {
        div(classes = "container") {
            span {
                h2(classes = "title") { +"Hello world!" }
            }
        }
    }



    class ArticlesState(var list: List<String> = listOf()) : RState
}
