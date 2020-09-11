//
//  ArticleCell.swift
//  iosApp
//
//  Created by Айрат Халилов on 11.09.2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import UIKit
import shared

class ArticleCell: UITableViewCell {

    // MARK: - UI elements

    lazy var titleLabel: UILabel = {
        let label = UILabel()
        return label
    }()

    // MARK: - Init

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
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
        selectionStyle = .none
    }

    private func addSubviews() {
        addSubview(titleLabel)
    }

    private func makeConstraints() {
        titleLabel.snp.makeConstraints { make in
            make.centerY.equalToSuperview()
            make.left.equalToSuperview().inset(8)
            make.right.equalToSuperview().inset(8)
        }
    }
    
    func configure(with article: ModelArticle) {
        titleLabel.text = article.title
    }

}
