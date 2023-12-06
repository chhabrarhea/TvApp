package com.rhea.tvapp.view

import android.content.res.AssetManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rhea.tvapp.data.MovieRepo
import com.rhea.tvapp.data.model.CastResponse
import com.rhea.tvapp.data.model.DetailResponse
import com.rhea.tvapp.data.model.Movie
import com.rhea.tvapp.data.model.Movies
import com.rhea.tvapp.util.Util
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val movieRepo: MovieRepo by lazy { MovieRepo() }

    val selectedMovie = MutableLiveData<Movie>()
    val isSideMenuOpened = MutableStateFlow(false)
    val clickedMovie = MutableLiveData<Movie>()

    private val _movieDetail = MutableLiveData<DetailResponse>()
    val movieDetail: LiveData<DetailResponse> = _movieDetail

    private val _castDetail = MutableLiveData<CastResponse>()
    val castDetail: LiveData<CastResponse> = _castDetail
    fun getListData(assets: AssetManager) : Movies {
        return Util.getJsonFromFile(assets,"movie_data.json")
    }

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            val response = movieRepo.getMovieDetails(id)
            Log.i("MainViewModel", response.toString())
            response.body()?.let {
                _movieDetail.postValue(it)
            }
        }
    }

    fun getMovieCast(id: Int) {
        viewModelScope.launch {
            movieRepo.getMovieCast(id).body()?.let {
                _castDetail.postValue(it)
            }
        }
    }
}