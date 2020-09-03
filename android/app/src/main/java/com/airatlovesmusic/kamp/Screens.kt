package com.airatlovesmusic.kamp

import com.airatlovesmusic.kamp.ui.ArticlesFragment
import com.airatlovesmusic.shared.router.Screen

object Screens {
    object Articles: Screen() {
        override fun getFragment() = ArticlesFragment()
    }
}