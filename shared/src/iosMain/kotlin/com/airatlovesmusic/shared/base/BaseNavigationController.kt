package com.airatlovesmusic.shared.base

import com.airatlovesmusic.shared.router.Router
import platform.UIKit.UINavigationController

abstract class BaseNavigationController: UINavigationController() {

    val router = Router(this)

    abstract val launchViewController: BaseViewController

    override fun viewDidLoad() {
        super.viewDidLoad()
        showViewController(launchViewController, null)
    }

}