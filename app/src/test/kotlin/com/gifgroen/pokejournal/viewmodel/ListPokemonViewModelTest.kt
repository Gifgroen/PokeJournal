package com.gifgroen.pokejournal.viewmodel

import com.gifgroen.domain.entities.Pokemon
import com.gifgroen.domain.usecases.GetPokemonUseCase
import com.gifgroen.domain.usecases.ListPokemonUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MockKExtension::class)
class ListPokemonViewModelTest {

    @MockK
    private lateinit var listUseCase: ListPokemonUseCase

    @MockK
    private lateinit var getUseCase: GetPokemonUseCase

    private lateinit var subject: ListPokemonViewModel

    private val dispatcher = TestCoroutineDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        subject = ListPokemonViewModel( // TODO: Inject useCases or create constructor params again!
            listUseCase = listUseCase, getUseCase = getUseCase
        )
    }

    @AfterEach
    fun teardown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPokemon return expected list of Entity`() = runBlockingTest {

        val bulbasaur = Pokemon(1, "bulbasaur")
        val ivysaur = Pokemon(2, "ivysaur")

        val pokemonList = listOf(bulbasaur, ivysaur)

        coEvery {
            getUseCase.getPokemonAsync(1)
        } returns bulbasaur

        coEvery {
            getUseCase.getPokemonAsync(2)
        } returns ivysaur

        coEvery {
            listUseCase.getPokemonAsync()
        } returns pokemonList

        subject.refresh()

        coVerifyAll {
            listUseCase.getPokemonAsync()
            getUseCase.getPokemonAsync(1)
            getUseCase.getPokemonAsync(2)
        }

        confirmVerified(listUseCase)
    }
}