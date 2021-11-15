package com.gifgroen.pokejournal.data.network.data

import com.gifgroen.pokejournal.data.network.api.PokeApi
import com.gifgroen.pokejournal.data.network.getIdFromPath
import com.gifgroen.pokejournal.domain.data.PokemonDataSource
import com.gifgroen.pokejournal.domain.entities.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRemoteDataSourceImpl(
    private val api: PokeApi = PokeApi()
): PokemonDataSource {

    override suspend fun getPokemonAsync(): List<Pokemon> {
        val pokemonList = api.listPokemonAsync()
        return pokemonList.results
            .map { Pokemon(id = getIdFromPath(it.url), name = it.name, sprite = "") }
    }

    override suspend fun getPokemonAsync(id: Int): Pokemon {
        val result = withContext(Dispatchers.Default) { api.getPokemonAsync(id) }
        println("sprites = ${result.sprites}")
        println("other = ${result.sprites.other}")
//        println("official frontDefault = ${result.sprites.other.officialArtwork?.frontDefault}")
//        println("frontDefault = ${result.sprites.front_default}")
        return Pokemon(id = result.id, name = result.name, sprite = result.sprites.other.officialArtwork?.frontDefault ?: "<empty artwork>")
    }
}
