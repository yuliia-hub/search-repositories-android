package com.test.searchrepositories.presentation.search

import com.test.searchrepositories.common.SingleLiveData
import com.test.searchrepositories.data.models.ui.RepositoryUIModel
import com.test.searchrepositories.presentation.base.BaseViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor(): BaseViewModel() {

    val repositoriesResult = SingleLiveData<List<RepositoryUIModel>>()

    fun handleInput(inputText: String?): Boolean =
        inputText?.takeIf { it.isNotEmpty() }?.let { q ->
            searchRepositories(q)
            true
        } ?: false

    private fun searchRepositories(q: String) {

    }

}