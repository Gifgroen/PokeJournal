package com.gifgroen.android.data

import com.gifgroen.android.api.PokeApi
import com.gifgroen.android.entity.NamedApiResource
import com.gifgroen.android.entity.NamedApiResult
import com.gifgroen.domain.data.PokemonDataSource
import com.gifgroen.domain.entities.Pokemon
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class PokemonRemoteDataSourceImplTest {

    @MockK
    private lateinit var api: PokeApi

    private val pokemon = Pokemon(1, "bulbasaur")

    private lateinit var subject: PokemonDataSource

    @BeforeEach
    fun setUp() {
        subject = PokemonRemoteDataSourceImpl(api)
    }

    @AfterEach
    fun teardown() {
        clearAllMocks()
    }

    @Test
    fun `getPokemon() returns expected list of Pokemon Entity`() {
        val baseLink = "http://pokeapi.co/api/v2/pokemon/"
        val url = "$baseLink${pokemon.id}"

        val resource = NamedApiResource(pokemon.name, url)
        val resultList = listOf(resource)
        val result = NamedApiResult(resultList.size, "", "", resultList)

        every {
            api.listPokemon()
        } returns Single.just(result)

        val testSubscriber = subject.getPokemon().test()

        testSubscriber.hasSubscription()
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
        testSubscriber.assertValue {
            it.size == 1 && it.first() == pokemon
        }

        verify(exactly = 1) {
            api.listPokemon()
        }
    }

    @Test
    fun `getPokemon() returns empty list of Pokemon`() {
        val resultList = emptyList<NamedApiResource>()
        val result = NamedApiResult(resultList.size, "", "", resultList)

        every {
            api.listPokemon()
        } returns Single.just(result)

        val testSubscriber = subject.getPokemon().test()

        testSubscriber.hasSubscription()
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
        testSubscriber.assertValue {
            it.isEmpty()
        }

        verify(exactly = 1) {
            api.listPokemon()
        }
        confirmVerified(api)
    }

    @Test
    fun `getPokemon() returns Empty object`() {
        val result = NamedApiResult(0, "", "", emptyList())

        every {
            api.listPokemon()
        } returns Single.just(result)

        val testSubscriber = subject.getPokemon().test()

        testSubscriber.hasSubscription()
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
        testSubscriber.assertValue {
            it.isEmpty()
        }

        verify(exactly = 1) {
            api.listPokemon()
        }
        confirmVerified(api)
    }

    @Test
    fun `getPokemon() throws Error`() {
        every {
            api.listPokemon()
        } returns Single.error(Throwable("An error occurred!"))

        val subject = subject.getPokemon().test()

        subject.hasSubscription()
        subject.assertError(Throwable::class.java)
        subject.assertNotComplete()
        subject.assertNoValues()

        verify(exactly = 1) {
            api.listPokemon()
        }
        confirmVerified(api)
    }
}