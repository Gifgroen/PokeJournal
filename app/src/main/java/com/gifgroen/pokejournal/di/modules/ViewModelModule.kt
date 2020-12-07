package com.gifgroen.pokejournal.di.modules

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.gifgroen.domain.usecases.GetPokemonUseCase
import com.gifgroen.domain.usecases.ListPokemonUseCase
import com.gifgroen.pokejournal.viewmodel.ListPokemonViewModel
import com.gifgroen.pokejournal.viewmodel.ListPokemonViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [UseCaseModule::class])
class ViewModelModule(
    private val owner: ViewModelStoreOwner
) {

    @Provides
    fun providesListPokemonPresenter(
        factory: ListPokemonViewModelFactory
    ): ListPokemonViewModel {
        return ViewModelProvider(owner, factory).get(ListPokemonViewModel::class.java)
    }

    @Provides
    fun providesListPokemonViewModelFactory(
        listPokemonUseCase: ListPokemonUseCase,
        getPokemonUseCase: GetPokemonUseCase
    ): ListPokemonViewModelFactory {
        return ListPokemonViewModelFactory(listPokemonUseCase, getPokemonUseCase)
    }
}