package com.gifgroen.android.data

import android.net.Uri
import com.gifgroen.android.api.PokeApi
import com.gifgroen.domain.data.PokemonDataSource
import com.gifgroen.domain.entities.Pokemon
import kotlinx.coroutines.*

class PokemonRemoteDataSourceImpl(private val api: PokeApi) : PokemonDataSource {

    override suspend fun getPokemonAsync(): List<Pokemon> {
        val pokemonList = api.listPokemonAsync()
        return pokemonList.results
            .map {
                /**
                 * TODO: add one-of mapper class
                 */
                val i = Uri.parse(it.url).lastPathSegment?.toInt() ?: -1
                Pokemon(i, it.name, "")
            }
    }

    override suspend fun getPokemonAsync(id: Int): Pokemon {
        val result = withContext(Dispatchers.IO) { api.getPokemonAsync(id) }
        return Pokemon(result.id, result.name, result.sprite())
    }
}