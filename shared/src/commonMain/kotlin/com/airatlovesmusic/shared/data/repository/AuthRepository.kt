package com.airatlovesmusic.shared.data.repository

import com.airatlovesmusic.shared.Constants.PreferencesKeys.KEY_TOKEN
import com.airatlovesmusic.shared.data.network.NetworkSource
import com.airatlovesmusic.shared.data.preferences.Preferences
import com.badoo.reaktive.single.doOnAfterSuccess

class AuthRepository(
    private val networkSource: NetworkSource,
    private val preferences: Preferences
) {

    fun isAuthorized() = preferences.getString(KEY_TOKEN) != null

    fun login(username: String, password: String) =
        networkSource.login(username, password)
            .doOnAfterSuccess { preferences.setString(KEY_TOKEN, it) }

    fun register(username: String, password: String) =
        networkSource.register(username, password)
            .doOnAfterSuccess { preferences.setString(KEY_TOKEN, it) }

}