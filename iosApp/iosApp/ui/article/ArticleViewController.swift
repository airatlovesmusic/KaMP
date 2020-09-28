//
//  ArticleViewController.swift
//  iosApp
//
//  Created by  Airat Khalilov on 10/09/2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import Foundation
import UIKit
import shared

class ArticleScreen: Screen {
    
    let id: String
    
    init(id: String) {
        self.id = id
    }
    
    override func getViewController() -> UIViewController {
        return ArticleViewController(id: id)
    }
    
}

class ArticleViewController: BaseViewController {
    
    private let articleView = ArticleView()
    private var featureComponent: ArticleFeatureComponent?
    
    init(id: String) {
        super.init(nibName: nil, bundle: nil)
        featureComponent = ArticleFeatureComponent(
            id: id,
            router: navigationController != nil ? Router(navigationController: navigationController!) : nil
        )
    }
    
    required init?(coder: NSCoder) {
        fatalError()
    }
    
    override var customView: UIView {
        return articleView
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        featureComponent?.bindListeners(
            stateListener: { [weak self] (state: ArticleFeatureComponent.State) in
                self?.renderState(state: state)
            },
            newsListener: { (news: ArticleFeatureComponent.News) in
                print(news)
            }
        )
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        featureComponent?.dispose()
    }
    
    private func renderState(state: ArticleFeatureComponent.State) {
        articleView.titleLabel.text = state.article?.title ?? ""
        articleView.urlLabel.text = state.article?.url ?? ""
    }
    
}
