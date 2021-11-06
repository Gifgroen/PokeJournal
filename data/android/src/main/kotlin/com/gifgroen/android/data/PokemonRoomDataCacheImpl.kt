package com.gifgroen.android.data

import com.gifgroen.domain.data.PokemonDataCache
import com.gifgroen.domain.entities.Pokemon

class PokemonRoomDataCacheImpl(
    val cache: List<Pokemon> = emptyList()
) : PokemonDataCache {

    override fun read(): List<Pokemon> {
        return emptyList()
    }

    override fun create(pokemon: Pokemon) {

    }

    override fun read(id: Int): Pokemon {
        return Pokemon(0, "")
    }

    override fun update(pokemon: Pokemon) {

    }

    override fun delete(pokemon: Pokemon) {

    }

    override fun delete(id: Int) {

    }
}
