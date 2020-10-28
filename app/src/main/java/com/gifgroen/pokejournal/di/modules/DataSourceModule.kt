package com.gifgroen.pokejournal.di.modules

import com.gifgroen.android.api.PokeApi
import com.gifgroen.android.data.PokemonRemoteDataSourceImpl
import com.gifgroen.android.data.PokemonRoomDataCacheImpl
import com.gifgroen.domain.data.PokemonDataCache
import com.gifgroen.domain.data.PokemonDataSource
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class DataSourceModule {

    @Provides
    fun providesDataSource(api: PokeApi): PokemonDataSource {
        return PokemonRemoteDataSourceImpl(api)
    }

    @Provides
    fun providesDataCache(): PokemonDataCache {
        return PokemonRoomDataCacheImpl()
    }
}