package com.gifgroen.pokejournal.ui.compose

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.pokejournal.viewmodel.ListPokemonViewModel

@Composable
fun PokeJournalScreen(
    viewModel: ListPokemonViewModel
) {
    val data = viewModel.getPokemon()
        .subscribeAsState(initial = emptyList())
    PokemonListing(
        state = data,
        modifier = Modifier.fillMaxSize()
    ) { index ->
        Log.e("PokeJournalScreen", "Clicked $index")
    }
}

@Composable
private fun PokemonListing(
    modifier: Modifier = Modifier,
    state: State<List<Pokemon>>,
    onItemClick: (Int) -> Unit
) {
    LazyColumnForIndexed(
        items = state.value
    ) { index, pokemon ->
        PokemonItem(
            item = pokemon,
            modifier = modifier,
            onItemClick = { onItemClick(index) }
        )
    }
}

@Composable
private fun PokemonItem(
    item: Pokemon,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.clickable(onClick = onItemClick)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "${item.id}")
            Text(text = item.name)
        }
    }
}