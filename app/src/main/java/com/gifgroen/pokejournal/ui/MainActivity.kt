package com.gifgroen.pokejournal.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.pokejournal.R
import com.gifgroen.pokejournal.di.components.DaggerAppComponent
import com.gifgroen.pokejournal.di.modules.ViewModelModule
import com.gifgroen.pokejournal.viewmodel.ListPokemonViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ListPokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.builder()
            .viewModelModule(ViewModelModule(this))
            .build()
            .inject(this)

        viewModel.getPokemon(::showPokemon, ::onError)
    }

    private fun showPokemon(pokemon: List<Pokemon>) {
        pokemon.forEach { println(it.name) }
    }

    private fun onError(t: Throwable) {
        println(t.message ?: "Something went wrong")
    }
}