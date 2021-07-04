package com.hackertronix.cinematic.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    val results: List<Movie>
)