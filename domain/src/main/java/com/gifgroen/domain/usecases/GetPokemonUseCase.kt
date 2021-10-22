package com.gifgroen.domain.usecases

import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.data.PokemonRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class GetPokemonUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun getPokemonAsync(id: Int): Pokemon = coroutineScope {
        pokemonRepository.getPokemonAsync(id)
    }
}