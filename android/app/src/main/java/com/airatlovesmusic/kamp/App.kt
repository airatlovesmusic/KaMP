package com.airatlovesmusic.kamp

import android.app.Application
import com.airatlovesmusic.shared.initKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

}