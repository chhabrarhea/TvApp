package com.rhea.tvapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewSelectedListener
import com.rhea.tvapp.data.Movie
import com.rhea.tvapp.util.ItemPresenter

class ListFragment: RowsSupportFragment() {

    private val rootAdapter: ArrayObjectAdapter by lazy {
        ArrayObjectAdapter(ListRowPresenter())
    }

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = rootAdapter
        bindData()
        setOnItemSelectedListener()
    }

    private fun setOnItemSelectedListener() {
        onItemViewSelectedListener = OnItemViewSelectedListener { _, data , _, _->
            if (data is Movie) {
                viewModel.selectedMovie.postValue(data)
            }
        }
    }

    private fun bindData() {
        viewModel.getListData(requireContext().assets).forEachIndexed { _, data ->
            val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter())

            data.movies.forEach {
                arrayObjectAdapter.add(it)
            }

            val headerItem = HeaderItem(data.title)
            val listRow = ListRow(headerItem, arrayObjectAdapter)
            rootAdapter.add(listRow)
        }
    }
}