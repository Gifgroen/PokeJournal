package com.gifgroen.pokejournal.di

import com.gifgroen.pokejournal.domain.data.PokemonRepository
import com.gifgroen.pokejournal.domain.usecases.GetPokemonUseCase
import com.gifgroen.pokejournal.domain.usecases.ListPokemonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun providesListPokemon(repository: PokemonRepository): ListPokemonUseCase =
        ListPokemonUseCase(repository)

    @Provides
    fun providesGetPokemon(repository: PokemonRepository): GetPokemonUseCase =
        GetPokemonUseCase(repository)
}
