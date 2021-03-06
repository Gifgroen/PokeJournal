package com.gifgroen.pokejournal.domain.data

import com.gifgroen.pokejournal.domain.entities.Pokemon

interface PokemonDataCache {

    fun read(): List<Pokemon>

    fun create(pokemon: Pokemon)

    fun read(id: Int): Pokemon

    fun update(pokemon: Pokemon)

    fun delete(pokemon: Pokemon)

    fun delete(id: Int)
}
