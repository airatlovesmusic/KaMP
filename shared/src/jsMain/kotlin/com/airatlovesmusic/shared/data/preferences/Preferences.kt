package com.airatlovesmusic.shared.data.preferences

import kotlinx.browser.localStorage

actual class Preferences {

    actual fun getString(key: String): String? =
        localStorage.getItem(key)

    actual fun setString(key: String, value: String?) =
        if (value != null) localStorage.setItem(key, value)
        else localStorage.removeItem(key)

}