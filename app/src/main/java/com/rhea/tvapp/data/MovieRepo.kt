package com.rhea.tvapp.data

import com.rhea.tvapp.data.api.ApiHelper

class MovieRepo {

    suspend fun getMovieDetails(id: Int) = ApiHelper.getMovieDetail(id)
    suspend fun getMovieCast(id: Int) = ApiHelper.getMovieCast(id)
}