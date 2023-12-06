package com.rhea.tvapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.distinctUntilChanged
import com.bumptech.glide.Glide
import com.rhea.tvapp.data.api.ApiHelper.IMAGE_BASE_URL
import com.rhea.tvapp.data.model.DetailResponse
import com.rhea.tvapp.data.model.Movie
import com.rhea.tvapp.databinding.ActivityDetailBinding
import com.rhea.tvapp.view.MainViewModel

class DetailActivity: FragmentActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setObservers()
        initData()
    }

    private fun initData() {
        val id = intent.getIntExtra(CLICKED_MOVIE_ID, -1)
        if (id != -1) {
            viewModel.getMovieDetail(id)
            viewModel.getMovieCast(id)
        }
    }

    private fun setObservers() {
        viewModel.movieDetail.distinctUntilChanged().observe(this) {
            initDetailViews(it)
        }
    }

    private fun initDetailViews(detail: DetailResponse) {
        binding.apply {
            title.text = detail.title
            subtitle.text = detail.overview
            Glide.with(this@DetailActivity)
                .load(IMAGE_BASE_URL + detail.poster_path)
                .into(bannerImageview)
        }
    }

    companion object {
        private const val CLICKED_MOVIE_ID = "clicked_movie_id"
        fun create(context: Context, movie: Movie): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(CLICKED_MOVIE_ID, movie.id)
            return intent
        }
    }
}