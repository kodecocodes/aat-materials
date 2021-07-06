package com.hackertronix.cinematic.data.sources

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hackertronix.cinematic.model.CastResponse
import com.hackertronix.cinematic.model.Movie

@Database(entities = [Movie::class, CastResponse::class], version = 1, exportSchema = false)
@TypeConverters(CastTypeConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val moviesDao: MoviesDao

    abstract val castDao: CastDao
}