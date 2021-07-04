package com.hackertronix.cinematic.data.di

import android.app.Application
import androidx.room.Room
import com.hackertronix.cinematic.data.MoviesDataRepository
import com.hackertronix.cinematic.data.repository.MoviesRepository
import com.hackertronix.cinematic.data.sources.cache.MoviesCache
import com.hackertronix.cinematic.data.sources.cache.MoviesDao
import com.hackertronix.cinematic.data.sources.cache.MoviesDatabase
import com.hackertronix.cinematic.data.sources.remote.MoviesRemote
import com.hackertronix.cinematic.data.sources.remote.TMDBService
import com.hackertronix.cinematic.data.store.MovieCacheStore
import com.hackertronix.cinematic.data.store.MoviesRemoteStore
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

    single { TMDBService.makeTMDBService() }

    single { provideDatabase(androidApplication()) }

    single { provideMoviesDao(get()) }

    factory<MoviesCache> { MovieCacheStore(get()) }

    factory<MoviesRemote> { MoviesRemoteStore(get()) }

    factory<MoviesRepository> { MoviesDataRepository(get(), get()) }
}