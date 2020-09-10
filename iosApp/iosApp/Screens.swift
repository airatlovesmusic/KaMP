//
//  Screens.swift
//  iosApp
//
//  Created by  Airat Khalilov on 10/09/2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import Foundation
import shared

class ScreensImpl: Screens {
    func articles() -> Screen {
        return ArticlesScreen()
    }
    func article(url: String) -> Screen {
        return ArticlesScreen()
    }
}
