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
    
    let url: String
    
    init(url: String) {
        self.url = url
    }
    
    override func getViewController() -> UIViewController {
        return ArticleViewController(url: url)
    }
    
}

class ArticleViewController: BaseViewController {
    
    private let articleView = ArticleView()
    private var featureComponent: ArticleFeatureComponent?
    
    init(url: String) {
        super.init(nibName: nil, bundle: nil)
        featureComponent = ArticleFeatureComponent(
            url: url,
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
