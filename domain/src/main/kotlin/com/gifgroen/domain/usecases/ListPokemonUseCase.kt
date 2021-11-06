package com.gifgroen.domain.usecases

import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.entities.Pokemon
import kotlinx.coroutines.coroutineScope

class ListPokemonUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun getPokemonAsync(): List<Pokemon> = coroutineScope {
        pokemonRepository.getPokemonAsync()
    }
}
