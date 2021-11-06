package com.gifgroen.android.data

import com.gifgroen.domain.data.PokemonDataSource
import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.entities.Pokemon

class PokemonRepositoryImpl(
    private val remoteDataStore: PokemonDataSource
) : PokemonRepository {

    override suspend fun getPokemonAsync(): List<Pokemon> = remoteDataStore.getPokemonAsync()

    override suspend fun getPokemonAsync(id: Int): Pokemon = remoteDataStore.getPokemonAsync(id)
}
