package com.airatlovesmusic.shared.data.preferences

import platform.Foundation.NSUserDefaults

actual class Preferences {

    private val userDefault: NSUserDefaults = NSUserDefaults(suiteName = "app")

    actual fun getString(key: String) =
        userDefault.stringForKey(key)

    actual fun setString(key: String, value: String) =
        userDefault.setObject(value, key)

}