package com.gifgroen.pokejournal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.usecases.GetPokemonUseCase
import com.gifgroen.domain.usecases.ListPokemonUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class ListPokemonViewModel(
    private val listUseCase: ListPokemonUseCase,
    private val getUseCase: GetPokemonUseCase
) : ViewModel() {

    val pokemonListFlow = MutableStateFlow<List<Pokemon>>(value = emptyList())

    fun refresh() = viewModelScope.launch (Dispatchers.Default) { pokemonListFlow.value = getPokemonAsync().await() }

    private suspend fun getPokemonAsync(): Deferred<List<Pokemon>> = viewModelScope.async {
        val pokemonList = listUseCase.getPokemonAsync()

        val jobs = pokemonList.map {
            async(Dispatchers.Unconfined) { getUseCase.getPokemonAsync(it.id) }
        }
        jobs.awaitAll()
    }
}