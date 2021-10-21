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
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
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

    @ExperimentalCoroutinesApi
    @Test
    fun `listPokemon returns expected list of Entity`() = runBlockingTest {
        val data: List<Pokemon> = listOf(
            Pokemon(1, "bulbasaur")
        )
        every {
            pokemonRepository.getPokemonAsync()
        } returns CompletableDeferred(data)

        val result = subject.getPokemonAsync().await()
        assert(data == result)

        verify(exactly = 1) {
            pokemonRepository.getPokemonAsync()
        }
        confirmVerified(pokemonRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `listPokemon returns empty list of Entity`() = runBlockingTest {
        val emptyList: List<Pokemon> = emptyList()
        every {
            pokemonRepository.getPokemonAsync()
        } returns CompletableDeferred(emptyList)

        val result = subject.getPokemonAsync().await()
        Assertions.assertTrue(result.isEmpty())

        verify(exactly = 1) {
            pokemonRepository.getPokemonAsync()
        }
        confirmVerified(pokemonRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `listPokemon returns Error`() = runBlockingTest {
        every {
            pokemonRepository.getPokemonAsync()
        } throws Throwable("getPokemonAsync() failed")

        Assertions.assertThrows(Throwable::class.java) {
            subject.getPokemonAsync()
        }


        verify(exactly = 1) {
            pokemonRepository.getPokemonAsync()
        }
        confirmVerified(pokemonRepository)
    }
}