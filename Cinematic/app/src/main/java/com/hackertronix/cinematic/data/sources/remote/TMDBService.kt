package com.hackertronix.cinematic.data.sources.remote

import com.hackertronix.cinematic.model.CastResponse
import com.hackertronix.cinematic.model.MoviesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/now_playing")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
    ): MoviesResponse

    @GET("movie/{movieId}/credits")
    suspend fun getCastDetails(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String,
    ): CastResponse

    companion object {
        const val API_BASE_URL = "https://api.themoviedb.org/3/"
        fun makeTMDBService(): TMDBService {
            val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(TMDBService::class.java)
        }
    }
}