//
//  ArticlesView.swift
//  iosApp
//
//  Created by  Airat Khalilov on 10/09/2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import UIKit

class ArticlesView: BaseView {
    
      lazy var tableView: UITableView = {
           let tableView = UITableView()
           tableView.backgroundColor = .white
           tableView.register(ArticleCell.self, forCellReuseIdentifier: "Article")
           return tableView
       }()

     override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
     }

     required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
     }

     private func commonInit() {
        setupStyle()
        addSubviews()
        makeConstraints()
    }

    private func setupStyle() {
        backgroundColor = .white
    }

    private func addSubviews() {
        addSubview(tableView)
    }

    private func makeConstraints() {
        tableView.snp.makeConstraints { make in
            make.edges.equalToSuperview()
        }
    }

    
}
