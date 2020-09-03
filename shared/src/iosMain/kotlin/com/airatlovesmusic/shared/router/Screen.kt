package com.airatlovesmusic.shared.router

import platform.UIKit.UIViewController

actual abstract class Screen {
    abstract fun getViewController(): UIViewController
}