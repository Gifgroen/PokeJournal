package com.gifgroen.android.data

import com.gifgroen.domain.data.PokemonDataCache
import com.gifgroen.domain.data.PokemonDataSource
import com.gifgroen.domain.data.PokemonRepository
import com.gifgroen.domain.entities.Pokemon
import io.mockk.Called
import io.mockk.clearAllMocks
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
class PokemonRepositoryImplTest {

    @MockK
    lateinit var remoteDataStore: PokemonDataSource

    @MockK
    private lateinit var localDataCache: PokemonDataCache

    private val pokemon = Pokemon(10, "charmander")

    private lateinit var subject: PokemonRepository

    @BeforeEach
    fun setUp() {
        subject = PokemonRepositoryImpl(remoteDataStore, localDataCache)
    }

    @AfterEach
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun `getPokemon() returns expected Entity`() {
        every {
            remoteDataStore.getPokemon()
        } returns Single.just(listOf(pokemon))

        val testSubscriber = subject.getPokemonAsync().test()

        testSubscriber.hasSubscription()
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
        testSubscriber.assertValue {
            it.size == 1 && it.first() == pokemon
        }

        verify(exactly = 1) {
            remoteDataStore.getPokemon()
        }
        verify { localDataCache.create(any()) wasNot Called }
        verify { localDataCache.read(any()) wasNot Called }
        verify { localDataCache.update(any()) wasNot Called }
        verify(exactly = 0) { localDataCache.delete(10) }
        verify(exactly = 0) { localDataCache.delete(pokemon) }
    }

    @Test
    fun `getPokemon() throws Error`() {
        every {
            remoteDataStore.getPokemon()
        } returns Single.error(Throwable("An error occurred"))

        val testSubscriber = subject.getPokemonAsync().test()

        testSubscriber.hasSubscription()
        testSubscriber.assertError(Throwable::class.java)
        testSubscriber.assertNotComplete()
        testSubscriber.assertNoValues()

        verify(exactly = 1) {
            remoteDataStore.getPokemon()
        }
    }
}