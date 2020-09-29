package com.airatlovesmusic.shared

import com.airatlovesmusic.shared.data.network.NetworkSource
import com.airatlovesmusic.shared.data.network.NetworkSourceImpl
import com.airatlovesmusic.shared.data.preferences.Preferences
import com.airatlovesmusic.shared.data.repository.ArticlesRepository
import com.airatlovesmusic.shared.data.repository.AuthRepository
import com.airatlovesmusic.shared.router.Screens
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(
    screens: Screens? = null,
    preferences: Preferences
) = startKoin {
    modules(
        module {
            single { screens }
            single { preferences }
        },
        multiPlatformModule
    )
}

val multiPlatformModule = module {
    single<NetworkSource> { NetworkSourceImpl(get()) }
    single { ArticlesRepository(get()) }
    single { AuthRepository(get(), get()) }
}