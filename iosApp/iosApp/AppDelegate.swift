//
//  AppDelegate.swift
//  iosApp
//
//  Created by Айрат Халилов on 08.09.2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import UIKit
import shared

class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?
    let appLauncher = AppLauncher()
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.makeKeyAndVisible()
        
        // DI
        KoinKt.doInitKoin(
            screens: ScreensImpl(),
            preferences: Preferences()
        )
        
        appLauncher.launchMainScreen()
        return true
    }

}

