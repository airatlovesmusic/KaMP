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
    
    private var feature: ArticlesFeatureComponent?
    private var adapter: ArticlesAdapter?
    
    override var customView: UIView {
        return articlesView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        feature = ArticlesFeatureComponent(
            router: navigationController != nil ? Router(navigationController: navigationController!) : nil
        )
        setUpTableView()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        feature?.bindListeners(
            stateListener: { [weak self] (state: ArticlesFeatureComponent.State) in
                self?.renderState(state: state)
            },
            newsListener: { (news: ArticlesFeatureComponent.News) in
                switch (news) {
                    case let failure as ArticlesFeatureComponent.NewsGetArticlesFailure: print(failure.error)
                    default: print("news - " + news.description)
                }
            }
        )
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        feature?.dispose()
    }
    
    private func renderState(state: ArticlesFeatureComponent.State) {
        if (state.articles?.isEmpty == false) {
            adapter?.array = state.articles ?? adapter!.array
            articlesView.tableView.reloadData()
        }
    }
    
    private func setUpTableView() {
        adapter = ArticlesAdapter()
        articlesView.tableView.dataSource = adapter
        articlesView.tableView.delegate = self
    }
    
}

extension ArticlesViewController: UITableViewDelegate {

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        feature?.goToArticle(url: adapter?.getArticle(indexPath).url ?? "")
    }

}

class ArticlesAdapter: NSObject, UITableViewDataSource, UITableViewDelegate {
    
    var array = Array<ModelArticle>()
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return array.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Article") as? ArticleCell
        cell?.configure(with: array[indexPath.row])
        return cell ?? UITableViewCell()
    }
    
    func getArticle(_ indexPath: IndexPath) -> ModelArticle {
        return array[indexPath.row]
    }
    
}
