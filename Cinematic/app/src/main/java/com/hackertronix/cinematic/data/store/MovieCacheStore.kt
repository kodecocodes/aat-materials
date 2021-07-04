package com.hackertronix.cinematic.data.store

import com.hackertronix.cinematic.data.sources.cache.MoviesCache
import com.hackertronix.cinematic.data.sources.cache.MoviesDao
import com.hackertronix.cinematic.model.Movie
import kotlinx.coroutines.flow.Flow

class MovieCacheStore constructor(private val dao: MoviesDao): MoviesCache {
    override suspend fun saveMovies(movies: List<Movie>) {
        dao.saveAllMovies(movies)
    }

    override suspend fun deleteAllMovies() {
        dao.deleteAllMovies()
    }

    override suspend fun getFavouriteMovies(): Flow<List<Movie>> {
        return dao.getFavouriteMovies()
    }

    override suspend fun getMovie(id: Int): Flow<Movie> {
        return dao.getMovie(id)
    }

    override suspend fun getPopularMovies(): Flow<List<Movie>> {
        return dao.getPopularMovies()
    }

    override suspend fun setFavourite(id: Int) {
        dao.setFavourite(id)
    }

    override suspend fun removeFavourite(id: Int) {
        dao.removeFavourite(id)
    }
}