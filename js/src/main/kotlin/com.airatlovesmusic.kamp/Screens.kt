package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp

import com.airatlovesmusic.shared.router.Screen
import com.airatlovesmusic.shared.router.Screens

class ScreensImpl: Screens {
    override fun article(url: String) = object : Screen() {}
    override fun articles() = object : Screen() {}
}