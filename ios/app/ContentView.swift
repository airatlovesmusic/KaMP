//
//  ContentView.swift
//  app
//
//  Created by Айрат Халилов on 31.08.2020.
//  Copyright © 2020 Айрат Халилов. All rights reserved.
//

import SwiftUI
import shared

struct ContentView: View {
    
    let apiClient = ApiClient()
    
    var body: some View {
        return Text(apiClient.getArticles().first ?? "1")
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
