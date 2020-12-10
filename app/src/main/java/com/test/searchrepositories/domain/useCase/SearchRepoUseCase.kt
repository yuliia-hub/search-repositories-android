package com.test.searchrepositories.domain.useCase

import com.test.searchrepositories.data.models.server.ListResponseModel
import com.test.searchrepositories.data.models.server.RepositoryModel
import com.test.searchrepositories.data.network.api.ApiResponseModel
import com.test.searchrepositories.domain.repository.IRepoRepository
import com.test.searchrepositories.presentation.base.BaseUseCase
import javax.inject.Inject

class SearchRepoUseCase @Inject constructor(
    private val repository: IRepoRepository
): BaseUseCase<ListResponseModel<RepositoryModel>>() {

    private var query: String = ""
    private var page: Int = -1
    private var perPage: Int = -1
    private var sort: String = ""
    private var order: String = ""

    fun setData(query: String, sort: String, order: String, page: Int, perPage: Int) {
        this.query = query
        this.sort = sort
        this.order = order
        this.page = page
        this.perPage = perPage
    }

    override suspend fun execute() : ApiResponseModel<ListResponseModel<RepositoryModel>> =
         repository.searchRepositories(query, sort, order, page, perPage)
}