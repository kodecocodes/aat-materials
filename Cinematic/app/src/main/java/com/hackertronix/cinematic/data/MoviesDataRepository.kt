package com.hackertronix.cinematic.data

import com.hackertronix.cinematic.data.repository.MoviesRepository
import com.hackertronix.cinematic.data.sources.cache.MoviesCache
import com.hackertronix.cinematic.data.sources.remote.MoviesRemote
import com.hackertronix.cinematic.model.Cast
import com.hackertronix.cinematic.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MoviesDataRepository(
    private val remoteStore: MoviesRemote,
    private val cacheStore: MoviesCache,
) : MoviesRepository {
    override suspend fun saveMovies(movies: List<Movie>) {
        cacheStore.saveMovies(movies)
    }

    override suspend fun deleteAllMovies() {
        cacheStore.deleteAllMovies()
    }

    override suspend fun getFavouriteMovies(): Flow<List<Movie>> {
        return cacheStore.getFavouriteMovies()
    }

    override suspend fun getPopularMovies(): Flow<List<Movie>> {
        val response = remoteStore.getPopularMovies()
        cacheStore.saveMovies(response.results)
        return cacheStore.getPopularMovies()
    }

    override suspend fun setFavourite(id: Int) {
        cacheStore.setFavourite(id)
    }

    override suspend fun removeFavourite(id: Int) {
        cacheStore.removeFavourite(id)
    }

    override suspend fun getMovie(id: Int): Flow<Movie> {
        return cacheStore.getMovie(id)
    }

    override suspend fun getCastDetails(id: Int): List<Cast> {
        val response = remoteStore.getCastDetails(id.toString())
        return response.cast
    }
}