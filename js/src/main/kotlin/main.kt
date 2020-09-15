package com.airatlovesmusic.kamp

import com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.ScreensImpl
import com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.app
import com.airatlovesmusic.shared.initKoin
//import com.airatlovesmusic.shared.initKoin
import kotlinx.browser.document
import react.dom.render

fun main() {
    initKoin(screens = ScreensImpl())
    render(document.getElementById("root")) {
        app()
    }
}