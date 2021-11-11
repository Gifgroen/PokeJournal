import SwiftUI
import PokeapiNetwork

@main
struct PokeJournaliOSApp: App {

    public init() {
        let p = PokemonRepositoryImpl(
          remoteDataStore: PokemonRemoteDataSourceImpl(
            api: PokeApi()
          )
        )
        print("p = \(p.description)")
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
