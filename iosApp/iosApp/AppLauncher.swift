//
//  AppLauncher.swift
//  iosApp
//
//  Created by Айрат Халилов on 11.09.2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import Foundation
import UIKit
import shared

class AppLauncher {
    
    private func setRootViewController(controller: UIViewController, animatedWithOptions: UIView.AnimationOptions?) {
        guard let window = UIApplication.shared.keyWindow else {
            fatalError("No window in app")
        }
        if let animationOptions = animatedWithOptions, window.rootViewController != nil {
            window.rootViewController = controller
            UIView.transition(with: window, duration: 0.33, options: animationOptions, animations: {
            }, completion: nil)
        } else {
            window.rootViewController = controller
        }
    }

    func launchMainScreen() {
        // todo
        let token = UserDefaults(suiteName: "app")?.string(forKey: "token")
        let controller = token != nil ?
            UINavigationController(rootViewController: MainFlowViewController()) :
            UINavigationController(rootViewController: MainFlowViewController())
        setRootViewController(controller: controller, animatedWithOptions: nil)
    }

}
