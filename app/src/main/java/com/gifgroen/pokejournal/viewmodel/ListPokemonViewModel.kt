package com.gifgroen.pokejournal.viewmodel

import androidx.lifecycle.ViewModel
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.usecases.GetPokemonUseCase
import com.gifgroen.domain.usecases.ListPokemonUseCase
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ListPokemonViewModel(
    private val listUseCase: ListPokemonUseCase,
    private val getUseCase: GetPokemonUseCase
) : ViewModel() {

    fun getPokemon(): Single<List<Pokemon>> {
        return listUseCase.getPokemon()
            .observeOn(Schedulers.io())
            .flattenAsObservable { it }
            .flatMapSingle { fetchDetails(it.id) }
            .toList()
    }

    private fun fetchDetails(id: Int): @NonNull Single<Pokemon>? {
        return getUseCase.getPokemon(id)
//            .subscribeOn(Schedulers.io())
    }
}