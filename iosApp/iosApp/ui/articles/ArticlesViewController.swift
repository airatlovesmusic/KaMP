//
//  File.swift
//  iosApp
//
//  Created by  Airat Khalilov on 10/09/2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import Foundation
import UIKit
import shared

class ArticlesScreen: Screen {
    override func getViewController() -> UIViewController {
        return ArticlesViewController()
    }
}

class ArticlesViewController: BaseViewController {
    
    private let articlesView = ArticlesView()
    
    override var customView: UIView {
        return articlesView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let feature: ArticlesFeatureComponent = ArticlesFeatureComponent(
            stateListener: { [weak self] (state: ArticlesFeatureComponent.State) in
                self?.renderState(state: state)
            },
            newsListener: { (news: ArticlesFeatureComponent.News) in
                switch (news) {
                    case let failure as ArticlesFeatureComponent.NewsGetArticlesFailure: print(failure.error)
                    default: print("news - " + news.description)
                }
            },
            router: nil
        )
    }
    
    private func renderState(state: ArticlesFeatureComponent.State) {
        articlesView.helloWorldLabel.text = state.description()
    }
    
}
