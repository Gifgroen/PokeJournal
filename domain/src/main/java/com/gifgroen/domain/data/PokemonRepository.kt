package com.gifgroen.domain.data

import com.gifgroen.domain.entities.Pokemon
import kotlinx.coroutines.Deferred

interface PokemonRepository {

    suspend fun getPokemonAsync(): List<Pokemon>

    suspend fun getPokemonAsync(id: Int): Pokemon
}
