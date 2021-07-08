package com.hackertronix.cinematic.data.sources

import com.hackertronix.cinematic.model.Cast
import com.hackertronix.cinematic.model.CastResponse
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

    suspend fun getCastDetails(id: Int): List<Cast>

    suspend fun saveCast(cast: List<CastResponse>)
}