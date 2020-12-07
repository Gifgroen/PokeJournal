package com.gifgroen.pokejournal.di.modules

import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.usecases.GetPokemonUseCase
import com.gifgroen.domain.usecases.ListPokemonUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
class UseCaseModule {

    @Provides
    fun providesListPokemon(repository: PokemonRepository): ListPokemonUseCase {
        return ListPokemonUseCase(repository)
    }

    @Provides
    fun providesGetPokemon(repository: PokemonRepository): GetPokemonUseCase {
        return GetPokemonUseCase(repository)
    }

}