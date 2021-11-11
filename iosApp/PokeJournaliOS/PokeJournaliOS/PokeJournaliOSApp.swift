//
//  PokeJournaliOSApp.swift
//  PokeJournaliOS
//
//  Created by Karsten Westra on 11/11/2021.
//

import SwiftUI
import PokeapiNetwork

@main
struct PokeJournaliOSApp: App {

    val p = PokemonRepositoryImpl(
        remoteDataStore: PokemonRemoteDataSourceImpl(
            api: PokeApi()
        )
    )

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
