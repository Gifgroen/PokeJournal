package com.gifgroen.pokejournal.data.network.api

import com.gifgroen.pokejournal.data.network.entity.NamedApiResult
import com.gifgroen.pokejournal.data.network.entity.PokemonResult
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get

val client = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}

class PokeApi {

    suspend fun listPokemonAsync(): NamedApiResult {
        return client.get("https://pokeapi.co/api/v2/pokemon")
    }

    suspend fun getPokemonAsync(id: Int): PokemonResult {
        return client.get("https://pokeapi.co/api/v2/pokemon/$id")
    }
}