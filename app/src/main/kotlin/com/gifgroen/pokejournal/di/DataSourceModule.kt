package com.gifgroen.pokejournal.di

import com.gifgroen.pokejournal.data.network.data.PokemonRemoteDataSourceImpl
import com.gifgroen.pokejournal.domain.data.PokemonDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ActivityComponent::class, ViewModelComponent::class)
class DataSourceModule {

    @Provides
    fun providesDataSource(): PokemonDataSource = PokemonRemoteDataSourceImpl()
}
