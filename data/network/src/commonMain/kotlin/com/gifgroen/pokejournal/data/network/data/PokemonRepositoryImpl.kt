package com.gifgroen.pokejournal.data.network.data

import com.gifgroen.pokejournal.domain.data.PokemonDataSource
import com.gifgroen.pokejournal.domain.data.PokemonRepository
import com.gifgroen.pokejournal.domain.entities.Pokemon

class PokemonRepositoryImpl(
    private val remoteDataStore: PokemonDataSource
) : PokemonRepository {

    override suspend fun getPokemonAsync(): List<Pokemon> = remoteDataStore.getPokemonAsync()

    override suspend fun getPokemonAsync(id: Int): Pokemon = remoteDataStore.getPokemonAsync(id)
}
