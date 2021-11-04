package com.gifgroen.pokejournal.ui.compose

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gifgroen.domain.entities.Pokemon
import kotlinx.coroutines.flow.*

@Composable
fun PokeJournalScreen(
    state: State<List<Pokemon>>,
    onRefresh: () -> Unit = {}
) {
    onRefresh()
    PokemonListing(
        pokemonList = state.value,
        modifier = Modifier.fillMaxSize(),
        onItemClick = { pokemon -> Log.e("PokeJournalScreen", "Clicked $pokemon") }
    )
}

@Composable
internal fun PokemonListing(
    pokemonList: List<Pokemon>,
    modifier: Modifier = Modifier,
    onItemClick: (Pokemon) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(pokemonList) { pokemon ->
            PokemonItem(
                item = pokemon,
                onItemClick = { onItemClick(pokemon) },
                modifier = Modifier.fillMaxWidth()
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
        Text(
            text = "${item.id}",
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Text(text = item.name)
    }
}

@Preview(showSystemUi = true)
@Composable
fun PokeJournalScreenPreview() {
    val pokemonFlow: MutableState<List<Pokemon>> = remember {
        mutableStateOf(
            listOf(
                Pokemon(1, "Foo"),
                Pokemon(2, "Bar"),
                Pokemon(3, "Baz")
            )
        )
    }

    PokeJournalScreen(
        state = pokemonFlow
    )
}