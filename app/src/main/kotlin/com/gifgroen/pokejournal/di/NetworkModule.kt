package com.gifgroen.pokejournal.di

import com.gifgroen.pokejournal.data.network.api.PokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @Provides
    fun providesPokeApi(): PokeApi = PokeApi()

    @Provides
    @Named("baseUrl")
    fun baseUrl(): String = "https://pokeapi.co/api/"
}
