package com.gifgroen.android.data

import com.gifgroen.android.api.PokeApi
import com.gifgroen.domain.data.PokemonDataSource
import com.gifgroen.domain.entities.Pokemon
import io.reactivex.rxjava3.core.Single

class PokemonRemoteDataSource(private val api: PokeApi): PokemonDataSource {

    override fun getPokemon(): Single<List<Pokemon>> {
        return api.listPokemon()
            .flattenAsObservable { it.results }
            .map { Pokemon(1, it.name) }
            .toList()
    }

    override fun getPokemon(id: Int): Single<Pokemon> {
        TODO("Not yet implemented")
    }
}