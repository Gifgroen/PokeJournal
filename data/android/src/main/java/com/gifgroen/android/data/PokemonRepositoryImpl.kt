package com.gifgroen.android.data

import com.gifgroen.domain.data.PokemonDataCache
import com.gifgroen.domain.data.PokemonDataSource
import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.entities.Pokemon
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepositoryImpl(
    private val remoteDataStore: PokemonDataSource,
    private val localDataCache: PokemonDataCache
) : PokemonRepository {

    override suspend fun getPokemonAsync(): List<Pokemon> {
        val remotePokemon = withContext(Dispatchers.IO) { remoteDataStore.getPokemonAsync() }
            // TODO: return stream to DB, store remote and emit new results remotePokemon
        return remotePokemon
    }

    override suspend fun getPokemonAsync(id: Int): Pokemon {
        return remoteDataStore.getPokemonAsync(id)
    }
}