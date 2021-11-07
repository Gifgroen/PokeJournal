package com.gifgroen.pokejournal.di

import com.gifgroen.android.api.PokeApi
import com.gifgroen.android.data.PokemonRemoteDataSourceImpl
import com.gifgroen.domain.data.PokemonDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ActivityComponent::class, ViewModelComponent::class)
class DataSourceModule {

    @Provides
    fun providesDataSource(api: PokeApi): PokemonDataSource = PokemonRemoteDataSourceImpl(api)
}
