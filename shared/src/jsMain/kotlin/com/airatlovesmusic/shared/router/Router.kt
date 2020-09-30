package com.airatlovesmusic.shared.router

// TODO
actual open class Router(
    private val parentRouter: Router? = null
) {
    actual open fun goTo(screen: Screen) {}
    actual open fun goBack() {}
    actual open fun finishFlow() { parentRouter?.goBack() }
    actual open fun startFlow(screen: Screen) { parentRouter?.goTo(screen) }
}