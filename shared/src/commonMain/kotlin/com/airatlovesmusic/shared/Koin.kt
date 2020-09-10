package com.airatlovesmusic.shared

import com.airatlovesmusic.shared.data.network.networkSource
import com.airatlovesmusic.shared.data.repository.ArticlesRepository
import com.airatlovesmusic.shared.router.Screens
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(screens: Screens? = null) = startKoin {
    modules(
        multiPlatformModule,
        module {
            single { screens }
        }
    )
}

val multiPlatformModule = module {
    single { networkSource() }
    single { ArticlesRepository(get()) }
}