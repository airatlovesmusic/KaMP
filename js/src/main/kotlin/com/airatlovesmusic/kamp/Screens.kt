package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp

import com.airatlovesmusic.shared.router.Screen
import com.airatlovesmusic.shared.router.Screens

class ScreensImpl: Screens {
    override fun article(id: String) = object : Screen() {}
    override fun authFlow() = object: Screen() {}
    override fun login() = object: Screen() {}
    override fun register() = object: Screen() {}
    override fun mainFlow() = object: Screen() {}
    override fun articles() = object: Screen() {}
}