package com.gifgroen.pokejournal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gifgroen.domain.usecases.GetPokemonUseCase
import com.gifgroen.domain.usecases.ListPokemonUseCase

class ListPokemonViewModelFactory(
    private val listPokemonUseCase: ListPokemonUseCase,
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListPokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListPokemonViewModel(listPokemonUseCase, getPokemonUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}