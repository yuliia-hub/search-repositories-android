package com.test.searchrepositories.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.searchrepositories.databinding.FragmentSearchRepositoriesBinding
import com.test.searchrepositories.presentation.base.BaseFragment
import com.test.searchrepositories.presentation.search.adapter.RepositoriesResultAdapter

class SearchFragment: BaseFragment<FragmentSearchRepositoriesBinding>() {

    companion object {
        fun getInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }
    private val repositoriesAdapter = RepositoriesResultAdapter()

    private val searchListener: SearchView.OnQueryTextListener?
    get() = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean = viewModel.handleInput(query)
        override fun onQueryTextChange(newText: String?): Boolean = viewModel.handleInput(newText)
    }

    override fun bindViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchRepositoriesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            repositoriesResult.observe(viewLifecycleOwner) {repositoriesAdapter.setItems(it)}
        }
        initUI()
    }

    private fun initUI() {
        with(viewBinding) {
            fSearchView.setOnQueryTextListener(searchListener)
            fSearchRv.layoutManager = LinearLayoutManager(requireContext())
            fSearchRv.adapter = repositoriesAdapter
        }
    }
}