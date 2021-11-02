package com.gifgroen.pokejournal.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gifgroen.pokejournal.ui.compose.PokeJournalScreen
import com.gifgroen.pokejournal.viewmodel.ListPokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: ListPokemonViewModel = viewModel()
            PokeJournalScreen(
                state = viewModel.pokemonListFlow.collectAsState(initial = emptyList()),
                onRefresh = { viewModel.refresh() }
            )
        }
    }
}