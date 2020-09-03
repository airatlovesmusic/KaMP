package com.airatlovesmusic.shared.base

import com.airatlovesmusic.shared.base.BaseNavigationController
import platform.UIKit.UIViewController
import platform.UIKit.navigationController

abstract class BaseViewController: UIViewController() {

    private val router by lazy {
        (navigationController as? BaseNavigationController)?.router
    }

    fun goBack() {
        router?.goBack()
    }

}