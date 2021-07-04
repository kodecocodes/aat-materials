package com.hackertronix.cinematic.util

import com.hackertronix.cinematic.model.Movie

interface MovieListClickListener {
    fun onMovieClicked(movie: Movie)
}