package com.hackertronix.cinematic.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hackertronix.cinematic.data.sources.CastTypeConverter

@Entity(tableName = "castResponse")
data class CastResponse(
    @PrimaryKey
    val id: Int,
    @TypeConverters(CastTypeConverter::class)
    val cast: List<Cast> = emptyList()
)
