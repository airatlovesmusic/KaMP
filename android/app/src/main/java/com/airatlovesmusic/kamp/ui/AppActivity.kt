package com.airatlovesmusic.kamp.ui

import com.airatlovesmusic.shared.base.BaseActivity
import com.airatlovesmusic.shared.router.Screen
import com.airatlovesmusic.shared.router.Screens
import org.koin.core.inject

class AppActivity: BaseActivity() {

    private val screens by inject<Screens>()

    override val launchScreen: Screen
        get() = screens.articles()

}