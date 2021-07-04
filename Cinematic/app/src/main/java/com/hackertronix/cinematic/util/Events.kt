package com.hackertronix.cinematic.util

sealed class Events {
    object Loading: Events()

    object Done: Events()
}
