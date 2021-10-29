package com.gifgroen.domain

import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.usecases.ListPokemonUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
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
        coEvery {
            pokemonRepository.getPokemonAsync()
        } returns data

        val result = subject.getPokemonAsync()
        assert(data == result)

        coVerify(exactly = 1) {
            pokemonRepository.getPokemonAsync()
        }
        confirmVerified(pokemonRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `listPokemon returns empty list of Entity`() = runBlockingTest {
        val emptyList: List<Pokemon> = emptyList()
        coEvery {
            pokemonRepository.getPokemonAsync()
        } returns emptyList

        val result = subject.getPokemonAsync()
        Assertions.assertTrue(result.isEmpty())

        coVerify(exactly = 1) {
            pokemonRepository.getPokemonAsync()
        }
        confirmVerified(pokemonRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `listPokemon returns Error`() = runBlockingTest {
        coEvery {
            pokemonRepository.getPokemonAsync()
        } throws Throwable("getPokemonAsync() failed")

        val exception = try {
            subject.getPokemonAsync()
            null
        } catch (exception: Throwable){
            exception
        }
        Assertions.assertEquals(exception?.message, "getPokemonAsync() failed")

        coVerify(exactly = 1) {
            pokemonRepository.getPokemonAsync()
        }
        confirmVerified(pokemonRepository)
    }
}