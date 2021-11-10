package com.gifgroen.pokejournal.data.network.entity

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResult(val id: Int, val name: String, val sprites: Sprites) {
    fun sprite() = sprites.other.officialArtwork?.frontDefault
}

@Serializable
data class Sprites(val other: Other)

@Serializable
data class Other(val officialArtwork: Artwork? = null)

@Serializable
data class Artwork(val frontDefault: String)
