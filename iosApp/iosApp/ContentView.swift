//
//  ContentView.swift
//  iosApp
//
//  Created by Айрат Халилов on 08.09.2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import SwiftUI
import shared

struct ContentView: View {
    
    let feature: ArticlesFeatureComponent =
        ArticlesFeatureComponent(
            stateListener: { (state: ArticlesFeatureComponent.State) in
                print("state - " + state.description())
            },
            newsListener: { (news: ArticlesFeatureComponent.News) in
                switch (news) {
                    case let failure as ArticlesFeatureComponent.NewsGetArticlesFailure: print(failure.error)
                    default: print("news - " + news.description)
                }
            }
        )
    
    var body: some View {
        Text("Hello")
    }

    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
