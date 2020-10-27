package com.gifgroen.domain.data

import com.gifgroen.domain.entities.Pokemon
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface PokemonRepository {

    fun getPokemon(): Observable<List<Pokemon>>

    fun getPokemon(id: Int): Single<Pokemon>
}
