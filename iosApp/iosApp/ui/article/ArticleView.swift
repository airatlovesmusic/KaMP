//
//  ArticleView.swift
//  iosApp
//
//  Created by Айрат Халилов on 11.09.2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import UIKit

class ArticleView: UIView {
    
    lazy var title: UILabel = {
        let label = UILabel()
        return UILabel()
    }()
    
    lazy var url: UILabel = {
        let label = UILabel()
        return label
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
        addSubview(title)
        addSubview(url)
   }

   private func makeConstraints() {
        title.snp.makeConstraints { make in
            make.centerY.equalToSuperview()
            make.left.equalToSuperview()
            make.right.equalToSuperview()
        }
        url.snp.makeConstraints { make in
            make.top.equalTo(title.snp.bottom)
            make.centerX.equalToSuperview()
            make.leading.equalToSuperview().offset(8)
        }
   }

}
