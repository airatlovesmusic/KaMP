//
//  AppDelegate.swift
//  iosApp
//
//  Created by Айрат Халилов on 08.09.2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import UIKit
import shared

class ArticlesScreen: Screen {
    override func getViewController() -> UIViewController {
        return ArticlesViewController()
    }
}

class ScreensImpl: Screens {
    func articles() -> Screen {
        return ArticlesScreen()
    }
    func article(url: String) -> Screen {
        return ArticlesScreen()
    }
}

class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.makeKeyAndVisible()
        
        // DI
        KoinKt.doInitKoin(screens: ScreensImpl())
        
        launchMainScreen()
        return true
    }

    private func launchMainScreen() {
        guard let window = UIApplication.shared.keyWindow else { return }
        let controller = ArticlesViewController()
        window.rootViewController = controller
    }


}

