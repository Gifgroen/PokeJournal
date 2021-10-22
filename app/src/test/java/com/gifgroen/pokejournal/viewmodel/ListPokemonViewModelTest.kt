package com.gifgroen.pokejournal.viewmodel

import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.usecases.GetPokemonUseCase
import com.gifgroen.domain.usecases.ListPokemonUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ListPokemonViewModelTest {

    @MockK
    private lateinit var listUseCase: ListPokemonUseCase

    @MockK
    private lateinit var getUseCase: GetPokemonUseCase

    private lateinit var subject: ListPokemonViewModel

    @BeforeEach
    fun setUp() {
        subject = ListPokemonViewModel(listUseCase, getUseCase = getUseCase)
    }

    @AfterEach
    fun teardown() {
        clearAllMocks()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPokemon return expected list of Entity`() = runBlockingTest {

        val pokemonList = listOf(
            Pokemon(1, "bulbasaur"),
            Pokemon(2, "ivysaur")
        )

        coEvery {
            listUseCase.getPokemonAsync()
        } returns pokemonList

        subject.refresh()

        coVerify(exactly = 1) {
            listUseCase.getPokemonAsync()
        }
        coVerify(exactly = 2) {
            getUseCase.getPokemonAsync(any())
        }

        confirmVerified(listUseCase)
    }
}