package com.gifgroen.pokejournal.data.network.data

import com.gifgroen.pokejournal.data.network.api.PokeApi
import com.gifgroen.pokejournal.domain.data.PokemonDataSource
import com.gifgroen.pokejournal.domain.entities.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//import java.io.File

class PokemonRemoteDataSourceImpl(
    private val api: PokeApi = PokeApi()
): PokemonDataSource {

    override suspend fun getPokemonAsync(): List<Pokemon> {
        val pokemonList = api.listPokemonAsync()
        return pokemonList.results
            .map {
//                val file = File(it.url)
//                val id = file.getName().toInt()
                Pokemon(id = 1, name = it.name)
            }
    }

    override suspend fun getPokemonAsync(id: Int): Pokemon {
        val result = withContext(Dispatchers.Default) { api.getPokemonAsync(id) }
        return Pokemon(id = result.id, name = result.name, sprite = result.sprite())
    }
}
