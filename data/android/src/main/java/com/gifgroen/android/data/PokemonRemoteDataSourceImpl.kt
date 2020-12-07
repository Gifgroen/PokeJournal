package com.gifgroen.android.data

import android.net.Uri
import android.util.Log
import com.gifgroen.android.api.PokeApi
import com.gifgroen.domain.data.PokemonDataSource
import com.gifgroen.domain.entities.Pokemon
import io.reactivex.rxjava3.core.Single

class PokemonRemoteDataSourceImpl(private val api: PokeApi) : PokemonDataSource {

    override fun getPokemon(): Single<List<Pokemon>> {
        return api.listPokemon()
            .flattenAsObservable { it.results }
            .map {
                /**
                 * TODO: add one-of mapper class
                 */
                val i = Uri.parse(it.url).lastPathSegment?.toInt() ?: -1
                Pokemon(i, it.name, "")
            }
            .toList()
    }

    override fun getPokemon(id: Int): Single<Pokemon> {
        return api.getPokemon(id)
            .map {
                Pokemon(it.id, it.name, it.sprite())
            }
    }
}