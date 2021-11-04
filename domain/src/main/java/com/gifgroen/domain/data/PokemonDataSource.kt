package com.gifgroen.domain.data

import com.gifgroen.domain.entities.Pokemon

interface PokemonDataSource {

    suspend fun getPokemonAsync(): List<Pokemon>

    suspend fun getPokemonAsync(id: Int): Pokemon
}