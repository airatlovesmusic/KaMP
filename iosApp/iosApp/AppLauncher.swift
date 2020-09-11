//
//  AppLauncher.swift
//  iosApp
//
//  Created by Айрат Халилов on 11.09.2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import Foundation
import UIKit

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
        let controller = UINavigationController(rootViewController: ArticlesViewController())
        setRootViewController(controller: controller, animatedWithOptions: nil)
    }

}
