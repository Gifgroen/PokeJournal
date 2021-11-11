package com.gifgroen.pokejournal.data.network

import java.io.File

actual fun getIdFromPath(path: String): Int {
    return File(path).nameWithoutExtension.toInt()
}