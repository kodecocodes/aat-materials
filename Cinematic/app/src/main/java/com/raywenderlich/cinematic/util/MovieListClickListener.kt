package com.raywenderlich.cinematic.util

import com.raywenderlich.cinematic.model.Movie

interface MovieListClickListener {
  fun onMovieClicked(movie: Movie)
}