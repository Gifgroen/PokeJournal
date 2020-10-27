package com.gifgroen.domain.usecases

import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.data.PokemonRepository
import io.reactivex.rxjava3.core.Observable

class ListPokemon(private val pokemonRepository: PokemonRepository) {

    fun getPokemon(): Observable<List<Pokemon>> = pokemonRepository.getPokemon()
}