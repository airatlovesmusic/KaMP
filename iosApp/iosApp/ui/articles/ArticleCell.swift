//
//  ArticleCell.swift
//  iosApp
//
//  Created by Айрат Халилов on 11.09.2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import UIKit
import shared

extension ArticleCell {
    struct Constants {
        static let cellReuseIdentifier = "ArticleCell"
        static let titleLabelHorizontalInset: CGFloat = 8
    }
}


class ArticleCell: UITableViewCell {

    // MARK: - UI elements

    lazy var titleLabel = UILabel()

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
            make.leading.equalToSuperview().inset(Constants.titleLabelHorizontalInset)
            make.right.equalToSuperview().inset(Constants.titleLabelHorizontalInset)
        }
    }
    
    func configure(with article: ModelArticle) {
        titleLabel.text = article.title
    }

}
