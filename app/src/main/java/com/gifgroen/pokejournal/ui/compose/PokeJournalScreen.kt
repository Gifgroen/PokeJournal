package com.gifgroen.pokejournal.ui.compose

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.pokejournal.viewmodel.ListPokemonViewModel

@Composable
fun PokeJournalScreen(
    viewModel: ListPokemonViewModel
) {
    val data = remember { viewModel.pokemonListFlow }
    val state = data.collectAsState()
    viewModel.refresh()
    PokemonListing(
        state = state,
        modifier = Modifier.fillMaxSize()
    ) { pokemon ->
        Log.e("PokeJournalScreen", "Clicked $pokemon")
    }
}

@Composable
internal fun PokemonListing(
    modifier: Modifier = Modifier,
    state: State<List<Pokemon>>,
    onItemClick: (Pokemon) -> Unit
) {
    LazyColumn {
        items(state.value) { pokemon ->
            PokemonItem(
                item = pokemon,
                modifier = modifier,
                onItemClick = { onItemClick(pokemon) }
            )
        }
    }
}

@Composable
internal fun PokemonItem(
    item: Pokemon,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable(onClick = onItemClick)
            .padding(16.dp)
    ) {
        Text(text = "${item.id}")
        Text(text = item.name)
    }
}
