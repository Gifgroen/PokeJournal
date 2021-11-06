package com.gifgroen.android.api

import com.gifgroen.android.entity.NamedApiResult
import com.gifgroen.android.entity.PokemonResult
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("v2/pokemon")
    suspend fun listPokemonAsync(): NamedApiResult

    @GET("v2/pokemon/{id}")
    suspend fun getPokemonAsync(@Path("id") id: Int): PokemonResult
}
