package com.airatlovesmusic.shared

import com.airatlovesmusic.shared.data.network.ApiClient
import com.airatlovesmusic.shared.data.repository.ArticlesRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(multiPlatformModule)
}

fun initKoin() = initKoin {}

val multiPlatformModule = module {
    single { ApiClient() }
    single { ArticlesRepository(get()) }
}