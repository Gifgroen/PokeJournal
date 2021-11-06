package com.gifgroen.android.entity

data class NamedApiResult(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<NamedApiResource>
)
