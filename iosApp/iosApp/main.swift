//
//  main.swift
//  iosApp
//
//  Created by  Airat Khalilov on 10/09/2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import Foundation
import UIKit

private func getDelegateClassName() -> String {
    return NSStringFromClass(AppDelegate.self)
}

_ = UIApplicationMain(CommandLine.argc, CommandLine.unsafeArgv, nil, getDelegateClassName())
