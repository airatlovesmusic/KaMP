//
//  ArticlesViewProxy.swift
//  iosApp
//
//  Created by Айрат Халилов on 08.09.2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import Foundation
import shared

class ArticlesViewProxy: NSObject {
    
    @Published var model: State
    
    func render(model: State) {
        self.model = model
    }
    
}
