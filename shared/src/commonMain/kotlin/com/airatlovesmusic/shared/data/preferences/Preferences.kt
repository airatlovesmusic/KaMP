package com.airatlovesmusic.shared.data.preferences

expect class Preferences {
    fun getString(key: String): String?
    fun setString(key: String, value: String?)
}