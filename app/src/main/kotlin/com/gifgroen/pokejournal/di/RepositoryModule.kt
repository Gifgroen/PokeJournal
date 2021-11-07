package com.gifgroen.pokejournal.di

import com.gifgroen.android.data.PokemonRepositoryImpl
import com.gifgroen.domain.data.PokemonDataSource
import com.gifgroen.domain.data.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ActivityComponent::class, ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun providesPokemonRepository(
        dataSource: PokemonDataSource,
    ): PokemonRepository = PokemonRepositoryImpl(dataSource)
}
