package com.rhea.tvapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.rhea.tvapp.data.Movie
import com.rhea.tvapp.databinding.ActivityDetailBinding

class DetailActivity: FragmentActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    companion object {
        private const val CLICKED_MOVIE = "clicked_movie"
        fun create(context: Context, movie: Movie): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(CLICKED_MOVIE, movie)
            return intent
        }
    }
}