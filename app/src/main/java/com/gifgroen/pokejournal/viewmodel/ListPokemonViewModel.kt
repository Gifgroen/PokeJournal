package com.gifgroen.pokejournal.viewmodel

import androidx.lifecycle.ViewModel
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.usecases.ListPokemonUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ListPokemonViewModel(
    private val listUseCase: ListPokemonUseCase,
    private val disposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {

    fun getPokemon(
        onNextPokemon: (List<Pokemon>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        disposable.apply {
            val single = listUseCase.getPokemon()
            add(single.subscribe(onNextPokemon, onError))
        }
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}