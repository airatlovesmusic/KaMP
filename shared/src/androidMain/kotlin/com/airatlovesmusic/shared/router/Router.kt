package com.airatlovesmusic.shared.router

import androidx.fragment.app.FragmentManager

actual open class Router constructor(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) {

    actual open fun goTo(screen: Screen) {
        fragmentManager.beginTransaction()
            .replace(containerId, screen.getFragment())
            .addToBackStack(screen.getFragment().hashCode().toString())
            .commit()
    }

    actual open fun goBack() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}