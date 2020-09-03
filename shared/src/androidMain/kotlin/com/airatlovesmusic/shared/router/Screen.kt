package com.airatlovesmusic.shared.router

import androidx.fragment.app.Fragment

actual abstract class Screen {
    abstract fun getFragment(): Fragment
}