package com.airatlovesmusic.kamp

import kotlinx.html.dom.append
import kotlinx.html.js.h1
import kotlinx.browser.document

fun main() {
    document.getElementById("app")
        ?.also { it.innerHTML = "" }
        ?.append {
            h1 { +"Hello world!" }
        }
}