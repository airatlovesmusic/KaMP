package com.airatlovesmusic.shared.router

expect open class Router {
    open fun goTo(screen: Screen)
    open fun goBack()
}