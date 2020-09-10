//
//  BaseView.swift
//  iosApp
//
//  Created by  Airat Khalilov on 10/09/2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import Foundation
import UIKit

class BaseView: UIView {
    
    let contentView = UIView()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addSubview(contentView)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("not been implemented")
    }
    
}
