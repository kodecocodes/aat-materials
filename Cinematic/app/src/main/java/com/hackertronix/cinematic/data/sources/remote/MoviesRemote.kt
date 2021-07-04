package com.hackertronix.cinematic.data.sources.remote

import com.hackertronix.cinematic.model.CastResponse
import com.hackertronix.cinematic.model.MoviesResponse

interface MoviesRemote {

    suspend fun getPopularMovies(): MoviesResponse

    suspend fun getCastDetails(id: String): CastResponse
}