package com.gifgroen.domain

import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.usecases.GetPokemonUseCase
import com.gifgroen.domain.usecases.ListPokemonUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Test

class ListPokemonTest {

    private val pokemonRepository: PokemonRepository = mockk()

    private val emptyList: List<Pokemon> = emptyList()

    @Test
    fun `listPokemon returns expected list of Entity`() {
        every {
            pokemonRepository.getPokemon()
        } returns Single.just(emptyList)

        val testSubscriber = ListPokemonUseCase(pokemonRepository).getPokemon()
            .test()

        testSubscriber.hasSubscription()
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
        testSubscriber.assertResult(emptyList)

        verify(exactly = 1) {
            pokemonRepository.getPokemon()
        }
    }

    @Test
    fun `listPokemon returns Error`() {
        every {
            pokemonRepository.getPokemon()
        } returns Single.error(Throwable())

        val testSubscriber = ListPokemonUseCase(pokemonRepository).getPokemon()
            .test()

        testSubscriber.hasSubscription()
        testSubscriber.assertNotComplete()
        testSubscriber.assertNoValues()
        verify(exactly = 1) {
            pokemonRepository.getPokemon()
        }
    }
}