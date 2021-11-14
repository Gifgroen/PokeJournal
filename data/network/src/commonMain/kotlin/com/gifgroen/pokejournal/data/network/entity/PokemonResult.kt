package com.gifgroen.pokejournal.data.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResult(
    val id: Int,
    val name: String,
    val sprites: Sprites
)

@Serializable
data class Sprites(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("other")
    val other: Other
)

@Serializable
data class Other(
    @SerialName("official-artwork")
    val officialArtwork: Artwork?
)

@Serializable
data class Artwork(
    @SerialName("front_default")
    val frontDefault: String
)
