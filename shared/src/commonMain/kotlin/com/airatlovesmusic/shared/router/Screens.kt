package com.airatlovesmusic.shared.router

expect abstract class Screen

interface Screens {
    fun articles(): Screen
    fun article(url: String): Screen
}

class ScreensImpl: Screens {

}

class Screen {
    fun getComponent() : Component
}