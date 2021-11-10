package com.gifgroen.pokejournal.data.network.entity

import kotlinx.serialization.Serializable

@Serializable
data class NamedApiResult(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<NamedApiResource>
)
