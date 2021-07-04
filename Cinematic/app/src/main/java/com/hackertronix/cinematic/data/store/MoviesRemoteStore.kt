package com.hackertronix.cinematic.data.store

import com.hackertronix.cinematic.BuildConfig
import com.hackertronix.cinematic.data.sources.remote.MoviesRemote
import com.hackertronix.cinematic.data.sources.remote.TMDBService
import com.hackertronix.cinematic.model.CastResponse
import com.hackertronix.cinematic.model.MoviesResponse

class MoviesRemoteStore constructor(private val movieService: TMDBService) : MoviesRemote {

    override suspend fun getPopularMovies(): MoviesResponse {
        return movieService.getPopularMovies(BuildConfig.TMDB_API_KEY)
    }

    override suspend fun getCastDetails(id: String): CastResponse {
        return movieService.getCastDetails(id, BuildConfig.TMDB_API_KEY)
    }
}