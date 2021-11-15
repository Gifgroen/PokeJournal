import SwiftUI
import PokeapiNetwork

struct ContentView: View {

    public init() {
        let p = PokemonRepositoryImpl(remoteDataStore: PokemonRemoteDataSourceImpl(api: PokeApi()))
        let useCase = ListPokemonUseCase(pokemonRepository: p)
        useCase.getPokemonAsync { (pokemonList: [DomainPokemon]?, error: Error?) in
            print("error = \(error)")
            guard let items = pokemonList else { return }
            for item in items {
                print("item = \(item)")
            }
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
