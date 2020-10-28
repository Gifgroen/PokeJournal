package com.gifgroen.android.data

import com.gifgroen.domain.data.PokemonDataCache
import com.gifgroen.domain.data.PokemonDataSource
import com.gifgroen.domain.entities.Pokemon
import io.mockk.*
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Test

class PokemonRepositoryImplTest {

    private val remoteDataStore: PokemonDataSource = mockk()

    private val localDataCache: PokemonDataCache = mockk()

    private val pokemon = Pokemon(10, "charmander")

    @Test
    fun `getPokemon() returns expected Entity`() {
        every {
            remoteDataStore.getPokemon()
        } returns Single.just(listOf(pokemon))

        val subject = PokemonRepositoryImpl(remoteDataStore, localDataCache)
            .getPokemon().test()

        subject.hasSubscription()
        subject.assertNoErrors()
        subject.assertComplete()
        subject.assertValue {
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

        val subject = PokemonRepositoryImpl(remoteDataStore, localDataCache)
            .getPokemon()
            .test()

        subject.hasSubscription()
        subject.assertError(Throwable::class.java)
        subject.assertNotComplete()
        subject.assertNoValues()

        verify(exactly = 1) {
            remoteDataStore.getPokemon()
        }
    }
}