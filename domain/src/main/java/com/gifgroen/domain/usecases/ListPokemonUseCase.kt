package com.gifgroen.domain.usecases

import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.data.PokemonRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class ListPokemonUseCase(private val pokemonRepository: PokemonRepository) {

    fun getPokemon(): Single<List<Pokemon>> = pokemonRepository.getPokemon()
}