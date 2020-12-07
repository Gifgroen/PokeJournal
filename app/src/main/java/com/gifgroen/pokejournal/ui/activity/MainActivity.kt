package com.gifgroen.pokejournal.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.gifgroen.pokejournal.di.components.DaggerAppComponent
import com.gifgroen.pokejournal.di.modules.ViewModelModule
import com.gifgroen.pokejournal.ui.compose.PokeJournalScreen
import com.gifgroen.pokejournal.viewmodel.ListPokemonViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ListPokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.builder()
            .viewModelModule(ViewModelModule(this))
            .build()
            .inject(this)

        setContent {
            PokeJournalScreen(viewModel)
        }
    }
}