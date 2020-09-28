package com.airatlovesmusic.kamp.ui

import com.airatlovesmusic.shared.base.BaseFlowFragment
import com.airatlovesmusic.shared.data.repository.AuthRepository
import com.airatlovesmusic.shared.router.Screens
import org.koin.core.inject

class MainFragment : BaseFlowFragment() {

    private val screens by inject<Screens>()
    private val authRepository by inject<AuthRepository>()

    override val launchScreen =
        if (authRepository.isAuthorized()) screens.articles()
        else screens.login()

}