//
//  BaseFlowViewController.swift
//  iosApp
//
//  Created by  Airat Khalilov on 29/09/2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import UIKit
import shared

class BaseFlowViewController: BaseViewController {
    
    var router: Router?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        router = Router(
            navigationController: navigationController!,
            parentRouter: parentRouter
        )
        guard let screen = getLaunchScreen() else { return }
        router?.goTo(screen: screen)
    }
    
    func getLaunchScreen() -> Screen? {
        return nil
    }
    
}
