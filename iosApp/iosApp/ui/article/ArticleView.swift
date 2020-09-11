//
//  ArticleView.swift
//  iosApp
//
//  Created by Айрат Халилов on 11.09.2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import UIKit

extension ArticleView {
    struct Constants {
        static let titleLabelHorizontalInset: CGFloat = 8
        static let urlLabelHorizontalInset: CGFloat = 8
    }
}

class ArticleView: UIView {
    
    lazy var titleLabel = UILabel()
    lazy var urlLabel = UILabel()
    
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
        addSubview(titleLabel)
        addSubview(urlLabel)
   }

   private func makeConstraints() {
        titleLabel.snp.makeConstraints { make in
            make.centerY.equalToSuperview()
            make.leading.equalToSuperview().offset(Constants.titleLabelHorizontalInset)
            make.trailing.equalToSuperview().offset(Constants.titleLabelHorizontalInset)
        }
        urlLabel.snp.makeConstraints { make in
            make.top.equalTo(titleLabel.snp.bottom)
            make.centerX.equalToSuperview()
            make.leading.equalToSuperview().offset(Constants.titleLabelHorizontalInset)
            make.trailing.equalToSuperview().offset(Constants.titleLabelHorizontalInset)
        }
   }

}
