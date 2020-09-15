package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp

import react.RBuilder
import react.RProps
import react.ReactElement
import react.dom.div

fun RBuilder.error(hasError: Boolean = false) = if (hasError) {
    div(classes = "alert alert-danger") {
        +"Failure."
    }
} else {
    object: ReactElement {
        override val props: RProps
            get() = object : RProps {}
    }
}