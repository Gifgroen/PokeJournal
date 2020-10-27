package com.gifgroen.domain

import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.usecases.ListPokemon
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.Test

class ListPokemonTest {

    private val pokemonRepository: PokemonRepository = mockk()

    private val emptyList: List<Pokemon> = emptyList()

    @Test
    fun `listPokemon returns expected list of Entity`() {
        every {
            pokemonRepository.getPokemon()
        } returns Observable.just(emptyList)

        val testSubscriber = ListPokemon(pokemonRepository).getPokemon()
            .test()

        testSubscriber.hasSubscription()
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
        testSubscriber.assertResult(emptyList)
    }
}