package com.rhea.tvapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.rhea.tvapp.R
import com.rhea.tvapp.data.Movie
import com.rhea.tvapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addListFragment()
        observeData()
    }

    private fun observeData() {
        viewModel.selectedMovie.observe(this) {
            bindSelectedMovieData(it)
        }
    }

    private fun bindSelectedMovieData(movie: Movie) {
        binding?.run{
            description.text = movie.overview
            title.text = movie.title
            subtitle.text = movie.vote_average.toString()
            val url = "https://www.themoviedb.org/t/p/w500" + movie.poster_path
            Glide.with(root.context)
                .load(url)
                .into(mainBannerImageview)
        }
    }

    private fun addListFragment() {
        childFragmentManager.beginTransaction().apply {
            replace(R.id.list_fragment, ListFragment())
            commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}