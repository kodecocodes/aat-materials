package com.raywenderlich.cinematic.data.di

import android.app.Application
import androidx.room.Room
import com.raywenderlich.cinematic.data.MoviesDataRepository
import com.raywenderlich.cinematic.data.repository.MoviesRepository
import com.raywenderlich.cinematic.data.sources.CastDao
import com.raywenderlich.cinematic.data.sources.MoviesCache
import com.raywenderlich.cinematic.data.sources.MoviesDao
import com.raywenderlich.cinematic.data.sources.MoviesDatabase
import com.raywenderlich.cinematic.data.store.MovieCacheStore
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

  fun provideDatabase(application: Application): MoviesDatabase {
    return Room.databaseBuilder(application, MoviesDatabase::class.java, "movies.db")
        .fallbackToDestructiveMigration()
        .build()
  }

  fun provideMoviesDao(database: MoviesDatabase): MoviesDao {
    return database.moviesDao
  }

  fun provideCastDao(database: MoviesDatabase): CastDao {
    return database.castDao
  }

  single { provideDatabase(androidApplication()) }

  single { provideMoviesDao(get()) }

  single { provideCastDao(get()) }

  factory<MoviesCache> { MovieCacheStore(get(), get()) }

  factory<MoviesRepository> { MoviesDataRepository(get()) }
}