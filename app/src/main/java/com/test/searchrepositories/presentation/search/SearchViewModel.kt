package com.test.searchrepositories.presentation.search

import androidx.lifecycle.viewModelScope
import com.test.searchrepositories.common.SingleLiveData
import com.test.searchrepositories.data.models.server.ListResponseModel
import com.test.searchrepositories.data.models.server.RepositoryModel
import com.test.searchrepositories.data.network.api.ApiResponseModel
import com.test.searchrepositories.data.network.api.ErrorResult
import com.test.searchrepositories.data.network.api.SuccessResult
import com.test.searchrepositories.domain.useCase.SearchRepoUseCase
import com.test.searchrepositories.presentation.base.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchRepoUseCase: SearchRepoUseCase
) : BaseViewModel() {

    companion object {
        private const val SORT = "stars"
        private const val ORDER = "desc"
        private const val PARALLEL_TASKS = 2
        private const val PER_PAGE = 15
        private const val SEARCH_DELAY = 1000L
    }

    val repositoriesResult = SingleLiveData<List<RepositoryModel>>()

    private val mutex = Mutex()
    private var searchJob: Job? = null
    private var searchScope = CoroutineScope(Dispatchers.Main)

    fun handleInput(inputText: String?): Boolean {
        searchJob?.cancel()
        searchJob = searchScope.launch {
            delay(SEARCH_DELAY)
            inputText?.takeIf { it.isNotEmpty() }?.let { q ->
                searchRepositories(q)
            } ?: let {
                repositoriesResult.value = emptyList()
            }
        }
        return false
    }

    private fun searchRepositories(query: String) {
        executeOnUI {
            var counter = 0
            val deferreds = mutableListOf<Deferred<ApiResponseModel<ListResponseModel<RepositoryModel>>>>()
            repeat(PARALLEL_TASKS) {
               mutex.withLock {
                   deferreds.add(getDataFromPageAsync(query, ++counter))
                }
            }
            showResults(deferreds.map { it.await() })
        }
    }

    private suspend fun getDataFromPageAsync(query: String, page: Int) =
        viewModelScope.async {
            withContext(Dispatchers.IO) {
                searchRepoUseCase.setData(query, SORT, ORDER, page, PER_PAGE)
                searchRepoUseCase.execute()
        }
    }

    private fun showResults(responses: List<ApiResponseModel<ListResponseModel<RepositoryModel>>>) {
        val result = mutableListOf<RepositoryModel>()
        responses.forEach { response ->
            when (response) {
                is SuccessResult -> response.body.items?.let { result.addAll(it) }
                is ErrorResult -> showError(response.message)
            }
        }
        repositoriesResult.value = result
    }

    override fun onCleared() {
        super.onCleared()
        searchScope.cancel()
    }
}