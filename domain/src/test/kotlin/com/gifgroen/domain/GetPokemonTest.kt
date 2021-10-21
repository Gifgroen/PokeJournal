package com.gifgroen.domain

import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.usecases.GetPokemonUseCase
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
        every {
            pokemonRepository.getPokemonAsync(1)
        } returns CompletableDeferred(pokemon)

        val result = subject.getPokemonAsync(1).await()
        assert(result == pokemon)

        verify(exactly = 1) {
            pokemonRepository.getPokemonAsync(1)
        }
        confirmVerified(pokemonRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPokemon returns Error`() = runBlockingTest {
        every {
            pokemonRepository.getPokemonAsync(any())
        } throws Exception("Error ocurred")


        Assertions.assertThrows(Exception::class.java) {
            subject.getPokemonAsync(1)
        }

        verify(exactly = 1) {
            pokemonRepository.getPokemonAsync(1)
        }
        confirmVerified(pokemonRepository)
    }
}