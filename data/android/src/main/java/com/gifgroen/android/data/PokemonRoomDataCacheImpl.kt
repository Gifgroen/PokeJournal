package com.gifgroen.android.data

import com.gifgroen.domain.data.PokemonDataCache
import com.gifgroen.domain.entities.Pokemon
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class PokemonRoomDataCacheImpl(
    val cache: List<Pokemon> = emptyList()
) : PokemonDataCache {

    override fun read(): Observable<List<Pokemon>> {
        TODO("Not yet implemented")
    }

    override fun create(pokemon: Pokemon): Completable {
        TODO("Not yet implemented")
    }

    override fun read(id: Int): Single<Pokemon> {
        TODO("Not yet implemented")
    }

    override fun update(pokemon: Pokemon): Completable {
        TODO("Not yet implemented")
    }

    override fun delete(pokemon: Pokemon): Completable {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Completable {
        TODO("Not yet implemented")
    }
}