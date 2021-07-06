package com.hackertronix.cinematic.data.sources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hackertronix.cinematic.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MoviesDao {

    @Query("SELECT * FROM movie")
    abstract fun getPopularMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE isFavourite = 1")
    abstract fun getFavouriteMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id =:id LIMIT 1")
    abstract fun getMovie(id: Int): Flow<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun saveAllMovies(movies: List<Movie>)

    @Query("DELETE FROM movie")
    abstract suspend fun deleteAllMovies()

    @Query("UPDATE movie SET isFavourite = 1 WHERE id=:id")
    abstract suspend fun setFavourite(id: Int)

    @Query("UPDATE movie SET isFavourite = 0 WHERE id=:id")
    abstract suspend fun removeFavourite(id: Int)

}