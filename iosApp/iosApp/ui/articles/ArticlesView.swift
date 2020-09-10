//
//  ArticlesView.swift
//  iosApp
//
//  Created by  Airat Khalilov on 10/09/2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import Foundation
import UIKit

class ArticlesView: BaseView {
    
    lazy var helloWorldLabel: UILabel = {
        let label = UILabel()
        label.text = "Hello World!"
        return label
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addSubview(helloWorldLabel)
    }
    
    required init?(coder: NSCoder) {
        fatalError("view doesn't implement init(:coder))")
    }
    
}
