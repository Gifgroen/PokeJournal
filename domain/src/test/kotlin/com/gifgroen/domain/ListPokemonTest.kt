package com.gifgroen.domain

import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.usecases.ListPokemonUseCase
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ListPokemonTest {

    @MockK
    private lateinit var pokemonRepository: PokemonRepository

    private lateinit var subject: ListPokemonUseCase

    @BeforeEach
    fun setUp() {
        subject = ListPokemonUseCase(pokemonRepository)
    }

    @AfterEach
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun `listPokemon returns expected list of Entity`() {
        val data: List<Pokemon> = listOf(
            Pokemon(1, "bulbasaur")
        )
        every {
            pokemonRepository.getPokemon()
        } returns Single.just(data)

        val testSubscriber = subject.getPokemon()
            .test()

        testSubscriber.hasSubscription()
        testSubscriber.assertResult(data)
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()

        verify(exactly = 1) {
            pokemonRepository.getPokemon()
        }
        confirmVerified(pokemonRepository)
    }

    @Test
    fun `listPokemon returns empty list of Entity`() {
        val emptyList: List<Pokemon> = emptyList()
        every {
            pokemonRepository.getPokemon()
        } returns Single.just(emptyList)

        val testSubscriber = subject.getPokemon()
            .test()

        testSubscriber.hasSubscription()
        testSubscriber.assertResult(emptyList)
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()

        verify(exactly = 1) {
            pokemonRepository.getPokemon()
        }
        confirmVerified(pokemonRepository)
    }

    @Test
    fun `listPokemon returns Error`() {
        every {
            pokemonRepository.getPokemon()
        } returns Single.error(Throwable())

        val testSubscriber = subject.getPokemon()
            .test()

        testSubscriber.hasSubscription()
        testSubscriber.assertNoValues()
        testSubscriber.assertNotComplete()
        testSubscriber.assertError(Throwable::class.java)

        verify(exactly = 1) {
            pokemonRepository.getPokemon()
        }
        confirmVerified(pokemonRepository)
    }
}