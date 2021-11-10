package com.gifgroen.pokejournal.di

//import com.gifgroen.android.data.PokemonRepositoryImpl
import com.gifgroen.pokejournal.domain.data.PokemonDataSource
import com.gifgroen.pokejournal.domain.data.PokemonRepository
import com.gifgroen.pokejournal.domain.entities.Pokemon
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
    ): PokemonRepository {
        return object: PokemonRepository {
            override suspend fun getPokemonAsync(): List<Pokemon> {
                return emptyList()
            }

            override suspend fun getPokemonAsync(id: Int): Pokemon {
                return Pokemon(0, "")
            }
        }
    }
}
