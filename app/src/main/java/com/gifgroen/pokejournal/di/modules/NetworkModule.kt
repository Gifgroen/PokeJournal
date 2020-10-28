package com.gifgroen.pokejournal.di.modules

import com.gifgroen.android.api.PokeApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    fun providesPokeApi(retrofit: Retrofit): PokeApi {
        return retrofit.create(PokeApi::class.java)
    }

    @Provides
    fun providesRetrofit(
        @Named("baseUrl") baseUrl: String,
        client: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .build()
    }

    @Provides
    @Named("baseUrl")
    fun baseUrl(): String {
        return "https://pokeapi.co/api/"
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    fun providesMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    fun providesRxJava3CallAdapterFactory(): RxJava3CallAdapterFactory = RxJava3CallAdapterFactory.create()
}