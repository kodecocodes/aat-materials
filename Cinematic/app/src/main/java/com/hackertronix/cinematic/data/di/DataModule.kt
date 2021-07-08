package com.hackertronix.cinematic.data.di

import android.app.Application
import androidx.room.Room
import com.hackertronix.cinematic.data.MoviesDataRepository
import com.hackertronix.cinematic.data.repository.MoviesRepository
import com.hackertronix.cinematic.data.sources.CastDao
import com.hackertronix.cinematic.data.sources.MoviesCache
import com.hackertronix.cinematic.data.sources.MoviesDao
import com.hackertronix.cinematic.data.sources.MoviesDatabase
import com.hackertronix.cinematic.data.store.MovieCacheStore
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