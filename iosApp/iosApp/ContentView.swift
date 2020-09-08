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
    
    var body: some View {
        Text("Hello")
    }
    
    let feature: ArticlesFeature = ArticlesFeature(
        articlesRepository: ArticlesRepository(apiClient: ApiClient()),
        stateListener: { (state) in
            print(state.description())
        },
        newsListener: { (news: News) in
            switch (news) {
                case let news as News.GetArticlesFailure: print("failure")
                default: print("default")
            }
        }
    )
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
