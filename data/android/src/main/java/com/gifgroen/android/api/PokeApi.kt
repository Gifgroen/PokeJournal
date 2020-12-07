package com.gifgroen.android.api

import com.gifgroen.android.entity.NamedApiResult
import com.gifgroen.android.entity.PokemonResult
import com.gifgroen.domain.entities.Pokemon
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("v2/pokemon")
    fun listPokemon(): Single<NamedApiResult>

    @GET("v2/pokemon/{id}")
    fun getPokemon(@Path("id") id: Int): Single<PokemonResult>
}