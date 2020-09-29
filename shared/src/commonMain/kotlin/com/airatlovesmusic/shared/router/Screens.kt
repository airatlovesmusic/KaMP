package com.airatlovesmusic.shared.router

expect abstract class Screen

interface Screens {
    fun mainFlow(): Screen
    fun articles(): Screen
    fun article(id: String): Screen

    fun authFlow(): Screen
    fun login(): Screen
    fun register(): Screen
}