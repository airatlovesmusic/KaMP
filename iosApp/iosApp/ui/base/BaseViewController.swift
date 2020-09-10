//
//  BaseViewController.swift
//  iosApp
//
//  Created by  Airat Khalilov on 10/09/2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import Foundation
import UIKit

class BaseViewController: UIViewController {
    
    private let baseView = BaseView()
    
    var customView: UIView {
        return UIView()
    }
    
    func makeDefaultConstraints(in viewController: UIViewController) {}
        
    override func loadView() {
        view = baseView
        view.backgroundColor = .white
        baseView.contentView.addSubview(customView)
        customView.backgroundColor = .white
    }
    
}
