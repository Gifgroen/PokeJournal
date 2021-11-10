package com.gifgroen.android.data

import com.gifgroen.pokejournal.domain.data.PokemonDataCache
import com.gifgroen.pokejournal.domain.data.PokemonDataSource
import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.entities.Pokemon
import io.mockk.*
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PokemonRepositoryImplTest {

    @MockK
    lateinit var remoteDataStore: com.gifgroen.pokejournal.domain.data.PokemonDataSource

    @MockK
    private lateinit var localDataCache: com.gifgroen.pokejournal.domain.data.PokemonDataCache

    private val pokemon = Pokemon(10, "charmander")

    private lateinit var subject: PokemonRepository

    @BeforeEach
    fun setUp() {
        subject = PokemonRepositoryImpl(remoteDataStore)
    }

    @AfterEach
    fun teardown() {
        clearAllMocks()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPokemon() returns expected Entity`() = runBlockingTest {
        coEvery {
            remoteDataStore.getPokemonAsync()
        } returns listOf(pokemon)


        val testResult = subject.getPokemonAsync()

        Assertions.assertTrue(testResult.size == 1 && testResult.first() == pokemon)

        coVerify(exactly = 1) {
            remoteDataStore.getPokemonAsync()
        }
//        verify { localDataCache.create(any()) wasNot Called }
//        verify { localDataCache.read(any()) wasNot Called }
//        verify { localDataCache.update(any()) wasNot Called }
//        verify(exactly = 0) { localDataCache.delete(10) }
//        verify(exactly = 0) { localDataCache.delete(pokemon) }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getPokemon() throws Error`() = runBlockingTest {
        coEvery {
            remoteDataStore.getPokemonAsync()
        } throws Throwable("An error occurred")

        val exception = try {
            subject.getPokemonAsync()
            null
        } catch (t: Throwable) {
            t
        }
        Assertions.assertEquals(exception?.message, "An error occurred")

        coVerify(exactly = 1) {
            remoteDataStore.getPokemonAsync()
        }
    }
}