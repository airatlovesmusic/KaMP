package com.airatlovesmusic.shared.router

import platform.UIKit.UINavigationController

actual open class Router constructor(
    private val navigationController: UINavigationController,
    private val parentRouter: Router? = null
) {

    actual open fun goTo(screen: Screen) {
        navigationController.showViewController(
            vc = screen.getViewController(),
            sender = navigationController
        )
    }

    actual open fun goBack() {
        navigationController.popViewControllerAnimated(true)
    }

    actual open fun finishFlow() {
        parentRouter?.goBack()
    }

}