package com.airatlovesmusic.kamp

import android.app.Application
import com.airatlovesmusic.shared.data.preferences.Preferences
import com.airatlovesmusic.shared.initKoin

class App: Application() {

    override fun onCreate() {
        initKoin(
            screens = ScreensImpl(),
            preferences = Preferences(this)
        )
        super.onCreate()
    }

}