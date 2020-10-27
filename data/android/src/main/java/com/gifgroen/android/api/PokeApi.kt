package com.gifgroen.android.api

import com.gifgroen.android.entity.NamedApiResult
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface PokeApi {

    companion object {
        val builder = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val api: PokeApi = builder.create(PokeApi::class.java)
    }

    @GET("v2/pokemon")
    fun listPokemon(): Single<NamedApiResult>
}