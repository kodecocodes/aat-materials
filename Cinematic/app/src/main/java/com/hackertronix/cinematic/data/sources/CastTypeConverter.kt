package com.hackertronix.cinematic.data.sources

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hackertronix.cinematic.model.Cast

class CastTypeConverter {

    @TypeConverter
    fun fromCast(cast: List<Cast>): String {
        return Gson().toJson(cast)
    }

    @TypeConverter
    fun toCast(cast: String?): List<Cast> {
        val listType = object : TypeToken<List<Cast?>?>() {}.type
        return try {
            Gson().fromJson(cast, listType)
        } catch (error: Error) {
            emptyList()
        }
    }
}