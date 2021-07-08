package com.raywenderlich.cinematic.model

import com.google.gson.annotations.SerializedName

data class Cast(

    @field:SerializedName("cast_id")
    val castId: Int,

    @field:SerializedName("character")
    val character: String,

    @field:SerializedName("gender")
    val gender: Int,

    @field:SerializedName("credit_id")
    val creditId: String,

    @field:SerializedName("known_for_department")
    val knownForDepartment: String,

    @field:SerializedName("original_name")
    val originalName: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("profile_path")
    val profilePath: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("adult")
    val adult: Boolean,

    @field:SerializedName("order")
    val order: Int
)
