package com.gifgroen.pokejournal.di

//import com.gifgroen.android.api.PokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class, ViewModelComponent::class)
class NetworkModule {

//    @Provides
//    fun providesPokeApi(retrofit: Retrofit): PokeApi = retrofit.create(PokeApi::class.java)

    @Provides
    fun providesRetrofit(
        @Named("baseUrl") baseUrl: String,
        client: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Provides
    @Named("baseUrl")
    fun baseUrl(): String = "https://pokeapi.co/api/"

    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    fun providesMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()
}
