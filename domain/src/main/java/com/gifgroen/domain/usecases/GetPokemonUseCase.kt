package com.gifgroen.domain.usecases

import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.data.PokemonRepository
import kotlinx.coroutines.Deferred

class GetPokemonUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun getPokemonAsync(id: Int): Pokemon = pokemonRepository.getPokemonAsync(id)
}