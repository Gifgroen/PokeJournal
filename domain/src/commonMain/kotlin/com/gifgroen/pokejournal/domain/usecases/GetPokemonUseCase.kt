package com.gifgroen.pokejournal.domain.usecases

import com.gifgroen.pokejournal.domain.data.PokemonRepository
import com.gifgroen.pokejournal.domain.entities.Pokemon
import kotlinx.coroutines.coroutineScope

class GetPokemonUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun getPokemonAsync(id: Int): Pokemon = coroutineScope {
        pokemonRepository.getPokemonAsync(id)
    }
}
