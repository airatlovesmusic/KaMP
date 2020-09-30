package com.airatlovesmusic.shared.router

import react.RComponent

actual abstract class Screen {
    abstract fun getComponent(parentRouter: Router): RComponent<*, *>
}