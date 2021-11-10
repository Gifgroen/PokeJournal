package com.gifgroen.pokejournal.domain

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}