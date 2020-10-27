package com.gifgroen.android.data

import com.gifgroen.domain.data.PokemonDataCache
import com.gifgroen.domain.data.PokemonDataSource
import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.entities.Pokemon
import io.reactivex.rxjava3.core.Single

class PokemonRepositoryImpl(
    private val remoteDataStore: PokemonDataSource,
    private val localDataCache: PokemonDataCache
): PokemonRepository {

    override fun getPokemon(): Single<List<Pokemon>> {
        return remoteDataStore.getPokemon()
    }

    override fun getPokemon(id: Int): Single<Pokemon> {
        return remoteDataStore.getPokemon(id)
    }
}