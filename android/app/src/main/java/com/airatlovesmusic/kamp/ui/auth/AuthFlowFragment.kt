package com.airatlovesmusic.kamp.ui.auth

import com.airatlovesmusic.shared.base.BaseFlowFragment
import com.airatlovesmusic.shared.router.Screen
import com.airatlovesmusic.shared.router.Screens
import org.koin.core.inject

class AuthFlowFragment: BaseFlowFragment() {

    private val screens by inject<Screens>()

    override val launchScreen: Screen
        get() = screens.login()

}