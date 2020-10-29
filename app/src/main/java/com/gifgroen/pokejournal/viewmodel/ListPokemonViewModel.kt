package com.gifgroen.pokejournal.viewmodel

import androidx.lifecycle.ViewModel
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.usecases.ListPokemonUseCase
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ListPokemonViewModel(
    private val listUseCase: ListPokemonUseCase
) : ViewModel() {

    fun getPokemon(): Single<List<Pokemon>> = listUseCase.getPokemon()
}