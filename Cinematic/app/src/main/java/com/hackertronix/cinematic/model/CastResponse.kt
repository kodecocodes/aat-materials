package com.hackertronix.cinematic.model

import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("cast")
    val cast: List<Cast> = emptyList()
)
