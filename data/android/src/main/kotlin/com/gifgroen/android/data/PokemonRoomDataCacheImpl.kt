package com.gifgroen.android.data

import com.gifgroen.domain.data.PokemonDataCache
import com.gifgroen.domain.entities.Pokemon

class PokemonRoomDataCacheImpl(
    val cache: List<Pokemon> = emptyList()
) : PokemonDataCache {

    override fun read(): List<Pokemon> {
//        TODO("Not yet implemented")
    }

    override fun create(pokemon: Pokemon) {
//        TODO("Not yet implemented")
    }

    override fun read(id: Int): Pokemon {
//        TODO("Not yet implemented")
    }

    override fun update(pokemon: Pokemon) {
//        TODO("Not yet implemented")
    }

    override fun delete(pokemon: Pokemon) {
//        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
//        TODO("Not yet implemented")
    }
}
