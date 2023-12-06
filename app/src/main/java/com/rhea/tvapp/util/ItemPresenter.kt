package com.rhea.tvapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.rhea.tvapp.data.api.ApiHelper
import com.rhea.tvapp.data.model.Movie
import com.rhea.tvapp.databinding.ItemViewBinding

class ItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val binding = ItemViewBinding.inflate(
            LayoutInflater.from(parent?.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        if (viewHolder is MovieViewHolder) {
            (item as? Movie)?.let {
                viewHolder.bind(it)
            }
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }

    inner class MovieViewHolder(private val binding: ItemViewBinding) : ViewHolder(binding.root) {
        init {
            val params = binding.root.layoutParams
            params.width = Util.getWidthInPercent(binding.root.context, 12)
            params.height = Util.getHeightInPercent(binding.root.context, 32)
        }

        fun bind(movie: Movie) {
            val url = ApiHelper.IMAGE_BASE_URL + movie.poster_path
            Glide.with(binding.root.context)
                .load(url)
                .into(binding.posterImage)
        }
    }
}