package com.gifgroen.domain

import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.usecases.GetPokemonUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.lang.NumberFormatException

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

    @ExperimentalCoroutinesApi
    @Test
    fun `getPokemon returns expected Entity`() = runBlockingTest {
        coEvery {
            pokemonRepository.getPokemonAsync(1)
        } returns pokemon

        val result = subject.getPokemonAsync(1)
        assert(result == pokemon)

        coVerify(exactly = 1) {
            pokemonRepository.getPokemonAsync(1)
        }
        confirmVerified(pokemonRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPokemon returns Error`() = runBlockingTest {
        coEvery {
            pokemonRepository.getPokemonAsync(1)
        } throws Exception("Error ocurred")

        val exception = try {
            subject.getPokemonAsync(1)
            null
        } catch (exception: Exception){
            exception
        }
        Assertions.assertEquals(exception?.message, "Error ocurred")

        coVerify(exactly = 1) {
            pokemonRepository.getPokemonAsync(1)
        }
        confirmVerified(pokemonRepository)
    }
}