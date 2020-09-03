package com.airatlovesmusic.shared.base

import com.airatlovesmusic.shared.router.Router
import platform.UIKit.UINavigationController

class BaseNavigationController: UINavigationController() {

    val router = Router(this)

}