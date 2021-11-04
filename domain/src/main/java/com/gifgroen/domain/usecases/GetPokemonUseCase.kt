package com.gifgroen.domain.usecases

import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.entities.Pokemon
import kotlinx.coroutines.coroutineScope

class GetPokemonUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun getPokemonAsync(id: Int): Pokemon = coroutineScope {
        pokemonRepository.getPokemonAsync(id)
    }
}