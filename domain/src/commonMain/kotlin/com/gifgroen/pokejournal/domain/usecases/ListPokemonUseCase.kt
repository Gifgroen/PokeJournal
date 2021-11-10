package com.gifgroen.pokejournal.domain.usecases

import com.gifgroen.pokejournal.domain.data.PokemonRepository
import com.gifgroen.pokejournal.domain.entities.Pokemon
import kotlinx.coroutines.coroutineScope

class ListPokemonUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun getPokemonAsync(): List<Pokemon> = coroutineScope {
        pokemonRepository.getPokemonAsync()
    }
}
