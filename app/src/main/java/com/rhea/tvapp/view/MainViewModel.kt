package com.rhea.tvapp.view

import android.content.res.AssetManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rhea.tvapp.data.Movie
import com.rhea.tvapp.data.Movies
import com.rhea.tvapp.util.Util
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel: ViewModel() {

    val selectedMovie = MutableLiveData<Movie>()
    val isSideMenuOpened = MutableStateFlow(false)

    fun getListData(assets: AssetManager) :Movies {
        return Util.getJsonFromFile(assets,"movie_data.json")
    }
}