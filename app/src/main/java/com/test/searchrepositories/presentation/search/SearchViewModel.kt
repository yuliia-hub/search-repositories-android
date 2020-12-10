package com.test.searchrepositories.presentation.search

import com.test.searchrepositories.common.SingleLiveData
import com.test.searchrepositories.data.models.server.ListResponseModel
import com.test.searchrepositories.data.models.server.RepositoryModel
import com.test.searchrepositories.data.network.api.ApiResponseModel
import com.test.searchrepositories.data.network.api.ErrorResult
import com.test.searchrepositories.data.network.api.SuccessResult
import com.test.searchrepositories.domain.useCase.SearchRepoUseCase
import com.test.searchrepositories.presentation.base.BaseViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchRepoUseCase: SearchRepoUseCase
) : BaseViewModel() {

    companion object {
        private const val SORT = "stars"
        private const val ORDER = "desc"
        private const val PARALLEL_TASKS = 2
        private const val PER_PAGE = 15
    }

    val repositoriesResult = SingleLiveData<List<RepositoryModel>>()
    val mutex = Mutex()

    fun handleInput(inputText: String?): Boolean =
        inputText?.takeIf { it.isNotEmpty() }?.let { q ->
            searchRepositories(q)
            true
        } ?: let {
            repositoriesResult.value = emptyList()
            false
        }

    private fun searchRepositories(query: String) {
        executeOnUI {
            var counter = 0
            val defs = mutableListOf<Deferred<ApiResponseModel<ListResponseModel<RepositoryModel>>>>()
            repeat(PARALLEL_TASKS) {
               mutex.withLock {
                   defs.add(getDataFromPageAsync(query, ++counter))
                }
            }
            showResults(defs.map { it.await() })
        }
    }

    private suspend fun getDataFromPageAsync(query: String, page: Int) = async {
        withContext(Dispatchers.IO) {
            searchRepoUseCase.setData(query, SORT, ORDER, page, PER_PAGE)
            searchRepoUseCase.execute()
        }
    }

    private fun showResults(
        responses: List<ApiResponseModel<ListResponseModel<RepositoryModel>>>
    ) {
        val result = mutableListOf<RepositoryModel>()
        responses.forEach { response ->
            when (response) {
                is SuccessResult -> response.body.items?.let { result.addAll(it) }
                is ErrorResult -> showError(response.message)
            }
        }
        repositoriesResult.value = result
    }
}