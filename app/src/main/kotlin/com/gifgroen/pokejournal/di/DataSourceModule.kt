package com.gifgroen.pokejournal.di

//import com.gifgroen.android.api.PokeApi
//import com.gifgroen.android.data.PokemonRemoteDataSourceImpl
import com.gifgroen.pokejournal.domain.data.PokemonDataSource
import com.gifgroen.pokejournal.domain.entities.Pokemon
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ActivityComponent::class, ViewModelComponent::class)
class DataSourceModule {

    @Provides
    fun providesDataSource(): PokemonDataSource {
         return object : PokemonDataSource {
             override suspend fun getPokemonAsync(): List<Pokemon> {
                 return emptyList()
             }

             override suspend fun getPokemonAsync(id: Int): Pokemon {
                 return Pokemon(0, "")
             }
         }
    }
}
