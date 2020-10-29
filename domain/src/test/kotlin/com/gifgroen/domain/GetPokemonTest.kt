package com.gifgroen.domain

import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.usecases.GetPokemonUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GetPokemonTest {

    @MockK
    private lateinit var pokemonRepository: PokemonRepository

    private val pokemon = Pokemon(1, "Bulbasaur")

    private lateinit var subject: GetPokemonUseCase

    @BeforeEach
    fun setUp() {
        subject = GetPokemonUseCase(pokemonRepository)
    }

    @AfterEach
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun `getPokemon returns expected Entity`() {
        every {
            pokemonRepository.getPokemon(1)
        } returns Single.just(pokemon)

        val testSubscriber = subject.getPokemon(1).test()

        testSubscriber.hasSubscription()
        testSubscriber.assertValue(pokemon)
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()

        verify(exactly = 1) {
            pokemonRepository.getPokemon(1)
        }
        confirmVerified(pokemonRepository)
    }

    @Test
    fun `getPokemon returns Error`() {
        every {
            pokemonRepository.getPokemon(1)
        } returns Single.error(Throwable())

        val testSubscriber = subject.getPokemon(1).test()

        testSubscriber.hasSubscription()
        testSubscriber.assertNoValues()
        testSubscriber.assertNotComplete()

        verify(exactly = 1) {
            pokemonRepository.getPokemon(1)
        }
        confirmVerified(pokemonRepository)
    }
}