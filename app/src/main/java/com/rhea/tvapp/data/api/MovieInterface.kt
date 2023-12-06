package com.rhea.tvapp.data.api

import com.rhea.tvapp.data.model.CastResponse
import com.rhea.tvapp.data.model.DetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterface {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id")id: Int, @Query("api_key") apiKey: String
    ):Response<DetailResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCast(
        @Path("movie_id")id: Int, @Query("api_key")apiKey: String
    ): Response<CastResponse>
}