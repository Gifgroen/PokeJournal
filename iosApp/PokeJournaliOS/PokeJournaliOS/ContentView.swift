import SwiftUI
import PokeapiNetwork

struct ContentView: View {

    public init() {
        let p = PokemonRepositoryImpl(remoteDataStore: PokemonRemoteDataSourceImpl(api: PokeApi()))
        p.getPokemonAsync { (pokemon: [DomainPokemon]?, err: Error?) in
            guard let pokemon = pokemon else { return }
            print("pokemon = \(pokemon)")
        }
    }

    var body: some View {
        Text("Hello, world!")
            .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
