package com.gifgroen.android.data

import com.gifgroen.android.api.PokeApi
import com.gifgroen.android.entity.NamedApiResource
import com.gifgroen.android.entity.NamedApiResult
import com.gifgroen.domain.entities.Pokemon
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Test

class PokemonRemoteDataSourceImplTest {

    private val api: PokeApi = mockk()

    private val pokemon = Pokemon(1, "bulbasaur")

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

        val subject = PokemonRemoteDataSourceImpl(api)
            .getPokemon()
            .test()

        subject.hasSubscription()
        subject.assertNoErrors()
        subject.assertComplete()
        subject.assertValue {
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

        val subject = PokemonRemoteDataSourceImpl(api)
            .getPokemon()
            .test()

        subject.hasSubscription()
        subject.assertNoErrors()
        subject.assertComplete()
        subject.assertValue {
            it.isEmpty()
        }

        verify(exactly = 1) {
            api.listPokemon()
        }
    }

    @Test
    fun `getPokemon() returns Empty object`() {
        val result = NamedApiResult(0, "", "", emptyList())

        every {
            api.listPokemon()
        } returns Single.just(result)

        val subject = PokemonRemoteDataSourceImpl(api)
            .getPokemon()
            .test()

        subject.hasSubscription()
        subject.assertNoErrors()
        subject.assertComplete()
        subject.assertValue {
            it.isEmpty()
        }

        verify(exactly = 1) {
            api.listPokemon()
        }
    }

    @Test
    fun `getPokemon() throws Error`() {
        every {
            api.listPokemon()
        } returns Single.error(Throwable("An error occurred!"))

        val subject = PokemonRemoteDataSourceImpl(api)
            .getPokemon()
            .test()

        subject.hasSubscription()
        subject.assertError(Throwable::class.java)
        subject.assertNotComplete()
        subject.assertNoValues()

        verify(exactly = 1) {
            api.listPokemon()
        }
    }
}