package com.gifgroen.android.entity

import com.squareup.moshi.Json

data class PokemonResult(val id: Int, val name: String, val sprites: Sprites) {
    fun sprite() = sprites.other.officialArtwork.frontDefault
}

data class Sprites(@field:Json(name = "other") val other: Other)

data class Other(@field:Json(name = "official-artwork") val officialArtwork: Artwork)

data class Artwork(@field:Json(name = "front_default") val frontDefault: String)