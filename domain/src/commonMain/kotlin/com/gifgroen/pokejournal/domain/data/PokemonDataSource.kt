package com.gifgroen.pokejournal.domain.data

import com.gifgroen.pokejournal.domain.entities.Pokemon

interface PokemonDataSource {

    suspend fun getPokemonAsync(): List<Pokemon>

    suspend fun getPokemonAsync(id: Int): Pokemon
}
