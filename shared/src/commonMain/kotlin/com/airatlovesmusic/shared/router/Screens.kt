package com.airatlovesmusic.shared.router

expect abstract class Screen

interface Screens {
    fun articles(): Screen
    fun article(id: Int): Screen
}