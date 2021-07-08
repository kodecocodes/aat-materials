package com.hackertronix.cinematic.data.sources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hackertronix.cinematic.model.CastResponse

@Dao
abstract class CastDao {

    @Query("SELECT * FROM castResponse WHERE :id = id")
    abstract fun getCastDetails(id: Int): CastResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveCast(cast: List<CastResponse>)
}