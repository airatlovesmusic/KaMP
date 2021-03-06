package com.airatlovesmusic.kamp.ui

import android.os.Bundle
import com.airatlovesmusic.shared.base.BaseActivity
import com.airatlovesmusic.shared.data.repository.AuthRepository
import com.airatlovesmusic.shared.router.Screen
import com.airatlovesmusic.shared.router.Screens
import org.koin.core.inject

class AppActivity: BaseActivity() {

    private val screens by inject<Screens>()
    private val authRepository by inject<AuthRepository>()

    override val launchScreen: Screen
        get() =
            if (authRepository.isAuthorized()) screens.mainFlow()
            else screens.authFlow()

}