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

    static var shared: ScreensImpl = {
        return ScreensImpl()
    }()
    
    // todo 
    func mainFlow() -> Screen {
        return ArticlesScreen()
    }
    
    func articles() -> Screen {
        return ArticlesScreen()
    }
    func article(id: String) -> Screen {
        return ArticleScreen(id: id)
    }
    func authFlow() -> Screen {
        return AuthFlowScreen()
    }
    func login() -> Screen {
        return LoginScreen()
    }
    func register() -> Screen {
        return RegisterScreen()
    }
}
