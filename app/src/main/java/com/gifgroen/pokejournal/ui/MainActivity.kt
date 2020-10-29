package com.gifgroen.pokejournal.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.pokejournal.R
import com.gifgroen.pokejournal.di.components.DaggerAppComponent
import com.gifgroen.pokejournal.di.modules.ViewModelModule
import com.gifgroen.pokejournal.viewmodel.ListPokemonViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ListPokemonViewModel

    private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.builder()
            .viewModelModule(ViewModelModule(this))
            .build()
            .inject(this)
    }

    override fun onStart() {
        super.onStart()
        if (disposable.isDisposed) {
            disposable = CompositeDisposable()
        }
        disposable.run {
            val single = viewModel.getPokemon()
            add(single.subscribe(::showPokemon, ::onError))
        }
    }

    override fun onStop() {
        super.onStop()
        disposable.dispose()
    }

    private fun showPokemon(pokemon: List<Pokemon>) {
        pokemon.forEach { println(it.name) }
    }

    private fun onError(t: Throwable) {
        println(t.message ?: "Something went wrong")
    }
}