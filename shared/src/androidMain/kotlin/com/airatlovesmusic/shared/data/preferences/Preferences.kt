package com.airatlovesmusic.shared.data.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

actual class Preferences(
    application: Application
) {

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("app", Context.MODE_PRIVATE)

    actual fun getString(key: String) =
        sharedPreferences.getString(key, null)

    actual fun setString(key: String, value: String) =
        sharedPreferences.edit { putString(key, value) }
}