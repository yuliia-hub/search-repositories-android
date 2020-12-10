package com.test.searchrepositories.domain.repository

import com.test.searchrepositories.data.models.server.ListResponseModel
import com.test.searchrepositories.data.models.server.RepositoryModel
import com.test.searchrepositories.data.network.api.ApiResponseModel

interface IRepoRepository {

    suspend fun searchRepositories(query: String, sort: String, order: String, page: Int, perPage: Int)
            : ApiResponseModel<ListResponseModel<RepositoryModel>>
}