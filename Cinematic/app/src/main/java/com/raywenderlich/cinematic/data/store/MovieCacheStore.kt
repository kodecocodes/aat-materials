package com.raywenderlich.cinematic.data.store

import com.raywenderlich.cinematic.data.sources.CastDao
import com.raywenderlich.cinematic.data.sources.MoviesCache
import com.raywenderlich.cinematic.data.sources.MoviesDao
import com.raywenderlich.cinematic.model.Cast
import com.raywenderlich.cinematic.model.CastResponse
import com.raywenderlich.cinematic.model.Movie
import kotlinx.coroutines.flow.Flow

class MovieCacheStore constructor(
    private val moviesDao: MoviesDao,
    private val castDao: CastDao
) : MoviesCache {
  override suspend fun saveMovies(movies: List<Movie>) {
    moviesDao.saveAllMovies(movies)
  }

  override suspend fun deleteAllMovies() {
    moviesDao.deleteAllMovies()
  }

  override suspend fun getFavouriteMovies(): Flow<List<Movie>> {
    return moviesDao.getFavouriteMovies()
  }

  override suspend fun getMovie(id: Int): Flow<Movie> {
    return moviesDao.getMovie(id)
  }

  override suspend fun getPopularMovies(): Flow<List<Movie>> {
    return moviesDao.getPopularMovies()
  }

  override suspend fun setFavourite(id: Int) {
    moviesDao.setFavourite(id)
  }

  override suspend fun removeFavourite(id: Int) {
    moviesDao.removeFavourite(id)
  }

  override suspend fun getCastDetails(id: Int): List<Cast> {
    return castDao.getCastDetails(id).cast
  }

  override suspend fun saveCast(cast: List<CastResponse>) {
    castDao.saveCast(cast)
  }
}