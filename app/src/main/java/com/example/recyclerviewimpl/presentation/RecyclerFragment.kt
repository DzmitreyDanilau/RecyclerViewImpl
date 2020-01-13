package com.example.recyclerviewimpl.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewimpl.R
import com.example.recyclerviewimpl.data.models.Item
import com.example.recyclerviewimpl.presentation.adapters.RecyclerViewAdapter
import com.example.recyclerviewimpl.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_recycler.*
import timber.log.Timber
import kotlin.reflect.KClass

class RecyclerFragment : BaseFragment<RecyclerViewModel>() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var progressBar: ProgressBar

    override val fragmentResId: Int
        get() = R.layout.fragment_recycler

    override fun getViewModelClass(): KClass<RecyclerViewModel> {
        return RecyclerViewModel::class
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("onAttach")
        (activity as RecyclerActivity).appComponent.inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initUi()
        observeObservables()
        viewModel.fetchData()
    }

    private fun initRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter()
        recycler_view.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun observeObservables() {
        viewModel.getState().observe(this) {
            when (it) {
                is RecyclerViewModel.State.Data -> {
                    hideProgress()
                    setItemList(it.list)
                }
                is RecyclerViewModel.State.Error -> {
                    hideProgress()
                    showToast(it.error.localizedMessage)
                }
                is RecyclerViewModel.State.Progress -> {
                    showProgress()
                }
            }
        }
    }

    private fun initUi() {
        progressBar = progress_bar
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass().java)
    }

    private fun setItemList(itemList: List<Item>) {
        recyclerViewAdapter.getItemList().addAll(itemList)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showToast(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
    }

}
