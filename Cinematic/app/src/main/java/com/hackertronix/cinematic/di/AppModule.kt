package com.hackertronix.cinematic.di

import com.hackertronix.cinematic.MoviesAdapter
import com.hackertronix.cinematic.details.CastAdapter
import com.hackertronix.cinematic.details.MovieDetailsViewModel
import com.hackertronix.cinematic.favorites.FavouriteMoviesViewModel
import com.hackertronix.cinematic.popular.PopularMoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { PopularMoviesViewModel(get()) }

    viewModel { FavouriteMoviesViewModel(get()) }

    viewModel { MovieDetailsViewModel(get()) }

    factory { MoviesAdapter() }

    factory { CastAdapter() }
}