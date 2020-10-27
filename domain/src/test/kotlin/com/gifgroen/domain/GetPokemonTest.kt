package com.gifgroen.domain

import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.usecases.GetPokemon
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Test

class GetPokemonTest {

    private val pokemonRepository: PokemonRepository = mockk()

    private val pokemon = Pokemon(1, "Bulbasaur")

    @Test
    fun `getPokemon returns expected Entity`() {
        every {
            pokemonRepository.getPokemon(1)
        } returns Single.just(pokemon)

        val testSubscriber = GetPokemon(pokemonRepository).getPokemon(1)
            .test()

        testSubscriber.hasSubscription()
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(pokemon)
    }
}