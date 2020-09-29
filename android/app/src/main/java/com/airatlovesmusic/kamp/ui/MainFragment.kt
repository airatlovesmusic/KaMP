package com.airatlovesmusic.kamp.ui

import com.airatlovesmusic.shared.base.BaseFlowFragment
import com.airatlovesmusic.shared.router.Screens
import org.koin.core.inject

class MainFragment : BaseFlowFragment() {

    private val screens by inject<Screens>()

    override val launchScreen = screens.articles()

}