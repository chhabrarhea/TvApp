package com.rhea.tvapp.data.api

import com.rhea.tvapp.util.Secret
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper {
    const val IMAGE_BASE_URL = "https://www.themoviedb.org/t/p/w500"
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val movieInterface: MovieInterface

    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieInterface = retrofit.create(MovieInterface::class.java)
    }

    suspend fun getMovieDetail(id: Int) = movieInterface.getMovieDetails(id, Secret.API_KEY)
    suspend fun getMovieCast(id: Int) = movieInterface.getMovieCast(id, Secret.API_KEY)
}