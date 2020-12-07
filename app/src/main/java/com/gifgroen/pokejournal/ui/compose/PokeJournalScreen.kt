package com.gifgroen.pokejournal.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.pokejournal.viewmodel.ListPokemonViewModel

@Composable
fun PokeJournalScreen(
    viewModel: ListPokemonViewModel
) {
    val data = viewModel.getPokemon()
        .subscribeAsState(initial = emptyList())

    PokemonListing(data)
}

@Composable
private fun PokemonListing(
    data: State<List<Pokemon>>,
    modifier: Modifier = Modifier
) {
    Box {
        LazyColumnFor(items = data.value) {
            Row(
                modifier = modifier.padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "${it.id}")
                Text(text = it.name)
            }
        }
    }
}

@Preview
@Composable
fun PokeJournalScreen() {
    val data =
        mutableStateOf(
            listOf(
                Pokemon(1, "Bulbasaur1", ""),
                Pokemon(2, "Bulbasaur2", ""),
                Pokemon(3, "Bulbasaur3", ""),
                Pokemon(4, "Bulbasaur4", ""),
                Pokemon(5, "Bulbasaur5", "")
            )
        )
    PokemonListing(data = data)
}