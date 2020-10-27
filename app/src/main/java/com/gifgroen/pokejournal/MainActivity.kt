package com.gifgroen.pokejournal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gifgroen.android.api.PokeApi
import com.gifgroen.android.data.PokemonRemoteDataSource
import com.gifgroen.android.data.PokemonRepositoryImpl
import com.gifgroen.android.data.PokemonRoomDataCache
import com.gifgroen.domain.data.PokemonDataCache
import com.gifgroen.domain.data.PokemonDataSource
import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.usecases.ListPokemon
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Data source implementations
        val dataSource: PokemonDataSource = PokemonRemoteDataSource(PokeApi.api)
        val dataCache: PokemonDataCache = PokemonRoomDataCache()
        // Repo
        val repo: PokemonRepository = PokemonRepositoryImpl(dataSource, dataCache)
        // UseCase
        val useCase = ListPokemon(repo)

        // Exec list useCase
        useCase.getPokemon()
            .subscribeOn(Schedulers.computation())
            .subscribe({
                for (pokemon in it) {
                    Log.e(TAG, pokemon.name)
                }
            }, { Log.e(TAG, it.message ?: "No err") })
    }
}