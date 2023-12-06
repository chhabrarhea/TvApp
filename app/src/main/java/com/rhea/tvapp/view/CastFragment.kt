package com.rhea.tvapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.lifecycle.distinctUntilChanged
import com.rhea.tvapp.data.model.CastResponse

class CastFragment: RowsSupportFragment() {

    private val rootAdapter: ArrayObjectAdapter by lazy {
        ArrayObjectAdapter(ListRowPresenter())
    }

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = rootAdapter
        setObservers()
    }

    private fun setObservers() {
        viewModel.castDetail.distinctUntilChanged().observe(this) {
            bindData(it.cast)
        }
    }

    private fun bindData(list: List<CastResponse.Cast>) {
        val arrayObjectAdapter = ArrayObjectAdapter(CastItemPresenter())

        list.forEach { content ->
            arrayObjectAdapter.add(content)
        }

        val headerItem = HeaderItem("Cast & Crew")
        val listRow = ListRow(headerItem, arrayObjectAdapter)
        rootAdapter.add(listRow)
    }
}