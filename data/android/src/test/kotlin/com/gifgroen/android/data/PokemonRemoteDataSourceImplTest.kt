package com.gifgroen.android.data

import com.gifgroen.android.api.PokeApi
import com.gifgroen.android.entity.NamedApiResource
import com.gifgroen.android.entity.NamedApiResult
import com.gifgroen.pokejournal.domain.data.PokemonDataSource
import com.gifgroen.domain.entities.Pokemon
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PokemonRemoteDataSourceImplTest {

    @MockK
    private lateinit var api: PokeApi

    private val pokemon = Pokemon(1, "bulbasaur")

    private lateinit var subject: com.gifgroen.pokejournal.domain.data.PokemonDataSource

    @BeforeEach
    fun setUp() {
        subject = PokemonRemoteDataSourceImpl(api)
    }

    @AfterEach
    fun teardown() {
        clearAllMocks()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPokemon() returns expected list of Pokemon Entity`() = runBlockingTest {
        val baseLink = "http://pokeapi.co/api/v2/pokemon/"
        val url = "$baseLink${pokemon.id}"

        val resource = NamedApiResource(pokemon.name, url)
        val resultList = listOf(resource)
        val result = NamedApiResult(resultList.size, "", "", resultList)

        coEvery {
            api.listPokemonAsync()
        } returns result

        val it = subject.getPokemonAsync()

        assertTrue(it.size == 1  && it.first() == pokemon)

        coVerify(exactly = 1) {
            api.listPokemonAsync()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPokemon() returns empty list of Pokemon`() = runBlockingTest {
        val resultList = emptyList<NamedApiResource>()
        val result = NamedApiResult(resultList.size, "", "", resultList)

        coEvery {
            api.listPokemonAsync()
        } returns result

        val testResult = subject.getPokemonAsync()

        assertTrue(testResult.isEmpty())

        coVerify(exactly = 1) {
            api.listPokemonAsync()
        }
        confirmVerified(api)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPokemon() returns Empty object`() = runBlockingTest {
        val result = NamedApiResult(0, "", "", emptyList())

        coEvery {
            api.listPokemonAsync()
        } returns result

        val testResult = subject.getPokemonAsync()
        assertTrue(testResult.isEmpty())

        coVerify(exactly = 1) {
            api.listPokemonAsync()
        }
        confirmVerified(api)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPokemon() throws Error`() = runBlockingTest {
        coEvery {
            api.listPokemonAsync()
        } throws Throwable("An error occurred!")

        val exception = try {
            subject.getPokemonAsync()
            null
        } catch(t: Throwable) {
            t
        }
        assertEquals(exception?.message, "An error occurred!")

        coVerify(exactly = 1) {
            api.listPokemonAsync()
        }
        confirmVerified(api)
    }
}