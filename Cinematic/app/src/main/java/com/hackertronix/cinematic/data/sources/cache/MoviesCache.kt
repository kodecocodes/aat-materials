package com.hackertronix.cinematic.data.sources.cache

import com.hackertronix.cinematic.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesCache {

    suspend fun saveMovies(movies: List<Movie>)

    suspend fun deleteAllMovies()

    suspend fun getFavouriteMovies(): Flow<List<Movie>>

    suspend fun getMovie(id: Int): Flow<Movie>

    suspend fun getPopularMovies(): Flow<List<Movie>>

    suspend fun setFavourite(id: Int)

    suspend fun removeFavourite(id: Int)

}