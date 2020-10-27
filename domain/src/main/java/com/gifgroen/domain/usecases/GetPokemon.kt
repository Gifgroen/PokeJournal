package com.gifgroen.domain.usecases

import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.data.PokemonRepository
import io.reactivex.rxjava3.core.Single

class GetPokemon(private val pokemonRepository: PokemonRepository) {

    fun getPokemon(id: Int): Single<Pokemon> = pokemonRepository.getPokemon(id)
}