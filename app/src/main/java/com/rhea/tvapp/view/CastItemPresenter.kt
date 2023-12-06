package com.rhea.tvapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rhea.tvapp.data.api.ApiHelper
import com.rhea.tvapp.data.model.CastResponse
import com.rhea.tvapp.databinding.CastItemViewBinding

class CastItemPresenter: Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return CastItemViewHolder(parent.context)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
       if (viewHolder is CastItemViewHolder && item is CastResponse.Cast) {
           viewHolder.bind(item)
       }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {}

    inner class CastItemViewHolder(private val bi: CastItemViewBinding): ViewHolder(bi.root) {

        constructor(context: Context) : this(
            CastItemViewBinding.inflate(LayoutInflater.from(context))
        )

        fun bind(cast: CastResponse.Cast) {
            val path = ApiHelper.IMAGE_BASE_URL + cast.profile_path
            Glide.with(bi.root.context)
                .load(path)
                .apply(RequestOptions.circleCropTransform())
                .into(bi.castImg)
        }
    }
}