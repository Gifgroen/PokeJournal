package com.gifgroen.pokejournal.viewmodel

import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.usecases.ListPokemonUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ListPokemonViewModelTest {

    @MockK
    private lateinit var listUseCase: ListPokemonUseCase

    private lateinit var subject: ListPokemonViewModel

    @BeforeEach
    fun setUp() {
        subject = ListPokemonViewModel(listUseCase)
    }

    @AfterEach
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun `getPokemon return expected list of Entity`() {

        val pokemonList = listOf(
            Pokemon(1, "bulbasaur"),
            Pokemon(2, "ivysaur")
        )

        every {
            listUseCase.getPokemon()
        } returns Single.just(pokemonList)

        val testSubscriber = subject.getPokemon().test()

        testSubscriber.hasSubscription()
        testSubscriber.assertValue { it == pokemonList }
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()

        verify(exactly = 1) {
            listUseCase.getPokemon()
        }

        confirmVerified(listUseCase)
    }

    @Test
    fun `getPokemon return empty list of Entity`() {
        val pokemonList = emptyList<Pokemon>()

        every {
            listUseCase.getPokemon()
        } returns Single.just(pokemonList)

        val testSubscriber = subject.getPokemon().test()

        testSubscriber.hasSubscription()
        testSubscriber.assertValue { it == pokemonList }
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()

        verify(exactly = 1) {
            listUseCase.getPokemon()
        }

        confirmVerified(listUseCase)
    }

    @Test
    fun `getPokemon throws error`() {
        every {
            listUseCase.getPokemon()
        } returns Single.error(Throwable("An error occurred"))

        val testSubscriber = subject.getPokemon().test()

        testSubscriber.hasSubscription()
        testSubscriber.assertNoValues()
        testSubscriber.assertError(Throwable::class.java)
        testSubscriber.assertNotComplete()

        verify(exactly = 1) {
            listUseCase.getPokemon()
        }

        confirmVerified(listUseCase)
    }
}