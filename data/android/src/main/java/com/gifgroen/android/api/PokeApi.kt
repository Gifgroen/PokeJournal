package com.gifgroen.android.api

import com.gifgroen.android.entity.NamedApiResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface PokeApi {

    @GET("v2/pokemon")
    fun listPokemon(): Single<NamedApiResult>
}