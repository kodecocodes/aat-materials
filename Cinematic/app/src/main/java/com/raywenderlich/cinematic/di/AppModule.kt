package com.raywenderlich.cinematic.di

import com.raywenderlich.cinematic.MoviesAdapter
import com.raywenderlich.cinematic.details.CastAdapter
import com.raywenderlich.cinematic.details.MovieDetailsViewModel
import com.raywenderlich.cinematic.favorites.FavouriteMoviesViewModel
import com.raywenderlich.cinematic.popular.PopularMoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

  viewModel { PopularMoviesViewModel(get()) }

  viewModel { FavouriteMoviesViewModel(get()) }

  viewModel { MovieDetailsViewModel(get()) }

  factory { MoviesAdapter() }

  factory { CastAdapter() }
}