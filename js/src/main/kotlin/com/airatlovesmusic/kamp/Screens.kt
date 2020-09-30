package com.airatlovesmusic.kamp.com.airatlovesmusic.kamp

import com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components.Article
import com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components.Articles
import com.airatlovesmusic.kamp.com.airatlovesmusic.kamp.components.base.BaseComponent
import com.airatlovesmusic.shared.router.Screen
import com.airatlovesmusic.shared.router.Screens
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

class ScreensImpl: Screens {
    override fun article(id: String) = object : Screen() {
        override fun getComponent() = Article(id)
    }

    override fun articles() = object: Screen() {
        override fun getComponent() = Articles()
    }

    override fun authFlow() = object: Screen() {
        override fun getComponent() = object: BaseComponent<RProps, RState>(object : RProps {}) {}
    }
    override fun login() = object: Screen() {
        override fun getComponent() = object: BaseComponent<RProps, RState>(object : RProps {}) {}
    }
    override fun register() = object: Screen() {
        override fun getComponent() = object: BaseComponent<RProps, RState>(object : RProps {}) {}
    }
    override fun mainFlow() = object: Screen() {
        override fun getComponent() = object: BaseComponent<RProps, RState>(object : RProps {}) {}
    }
}