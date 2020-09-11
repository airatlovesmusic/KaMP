//
//  Configurable.swift
//  iosApp
//
//  Created by Айрат Халилов on 11.09.2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//


protocol Configurable where Self: UIView {
    associatedtype ViewModel

    func configure(with viewModel: ViewModel)

}
