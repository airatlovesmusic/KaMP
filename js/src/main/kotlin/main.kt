package com.airatlovesmusic.kamp

import com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.ScreensImpl
import com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.app
import com.airatlovesmusic.shared.data.preferences.Preferences
import com.airatlovesmusic.shared.initKoin
import kotlinx.browser.document
import kotlinx.browser.window
import react.dom.render

fun main() {
    val koinComponent = initKoin(
        screens = ScreensImpl(),
        preferences = Preferences()
    )
    window.onload = {
        render(document.getElementById("root")) {
            app(koinComponent.koin)
        }
    }
}