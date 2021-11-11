package com.gifgroen.pokejournal.data.network

import platform.Foundation.NSURL
import platform.Foundation.lastPathComponent

actual fun getIdFromPath(path: String): Int {
    return NSURL(fileURLWithPath = path).lastPathComponent?.toInt() ?: 0
}