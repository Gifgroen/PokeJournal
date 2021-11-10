package com.gifgroen.pokejournal.data.network.entity

import kotlinx.serialization.Serializable

@Serializable
data class NamedApiResource(val name: String, val url: String)
