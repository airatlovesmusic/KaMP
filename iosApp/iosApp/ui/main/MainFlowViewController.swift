//
//  MainFlowViewController.swift
//  iosApp
//
//  Created by  Airat Khalilov on 29/09/2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import UIKit
import shared

class MainFlowViewController: BaseFlowViewController {
    
    override func getLaunchScreen() -> Screen? {
        return ScreensImpl.shared.articles()
    }
    
}
