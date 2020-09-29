//
//  AuthFlowViewController.swift
//  iosApp
//
//  Created by  Airat Khalilov on 29/09/2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import Foundation
import UIKit
import shared

class AuthFlowScreen: Screen {
    override func getViewController() -> UIViewController {
        return AuthFlowViewController()
    }
}

class AuthFlowViewController: BaseFlowViewController {
    
    override func getLaunchScreen() -> Screen {
        return ScreensImpl.shared.login()
    }
    
}
