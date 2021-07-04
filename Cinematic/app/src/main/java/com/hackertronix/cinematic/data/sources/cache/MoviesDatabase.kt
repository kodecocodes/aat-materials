package com.hackertronix.cinematic.data.sources.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hackertronix.cinematic.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao
}