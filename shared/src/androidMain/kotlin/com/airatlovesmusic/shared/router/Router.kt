package com.airatlovesmusic.shared.router

import androidx.fragment.app.FragmentManager

actual open class Router constructor(
    private val fragmentManager: FragmentManager,
    private val containerId: Int,
    private val parentRouter: Router? = null
) {

    actual open fun goTo(screen: Screen) {
        val fragment = screen.getFragment()
        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(fragment.hashCode().toString())
            .commit()
    }

    actual open fun goBack() {
        fragmentManager.popBackStack()
    }

    actual open fun finishFlow() {
        parentRouter?.goBack()
    }

    actual open fun startFlow(screen: Screen) {
        parentRouter?.goTo(screen)
    }

}