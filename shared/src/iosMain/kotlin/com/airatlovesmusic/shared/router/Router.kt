package com.airatlovesmusic.shared.router

import platform.UIKit.UINavigationController

actual open class Router constructor(
    private val navigationController: UINavigationController
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

}