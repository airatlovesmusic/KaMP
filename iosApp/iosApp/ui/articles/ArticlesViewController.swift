//
//  File.swift
//  iosApp
//
//  Created by  Airat Khalilov on 10/09/2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import Foundation
import UIKit

class ArticlesViewController: BaseViewController {
    
    private let articlesView = ArticlesView()
    
    override var customView: UIView {
        return articlesView
    }
    
}
