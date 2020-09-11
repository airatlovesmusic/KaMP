//
//  BaseView.swift
//  iosApp
//
//  Created by  Airat Khalilov on 10/09/2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import Foundation
import UIKit
import SnapKit

class BaseView: UIView {
    
    let contentView = UIView()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addSubview(contentView)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("not been implemented")
    }

    func makeDefaultConstraints(in viewController: UIViewController) {
        contentView.snp.makeConstraints { make in
            make.top.equalTo(viewController.topLayoutGuide.snp.bottom)
            make.bottom.equalTo(viewController.bottomLayoutGuide.snp.top)
            make.leading.trailing.equalToSuperview()
        }
    }
    
}
