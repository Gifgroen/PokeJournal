package com.gifgroen.pokejournal.di.modules

import com.gifgroen.android.data.PokemonRepositoryImpl
import com.gifgroen.domain.data.PokemonDataCache
import com.gifgroen.domain.data.PokemonDataSource
import com.gifgroen.domain.data.PokemonRepository
import dagger.Module
import dagger.Provides

@Module(includes = [DataSourceModule::class])
class RepositoryModule {

    @Provides
    fun providesPokemonRepository(
        dataSource: PokemonDataSource,
        dataCache: PokemonDataCache
    ): PokemonRepository {
        return PokemonRepositoryImpl(dataSource, dataCache)
    }
}