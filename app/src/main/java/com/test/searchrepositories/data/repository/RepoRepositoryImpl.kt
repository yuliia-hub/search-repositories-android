package com.test.searchrepositories.data.repository

import com.test.searchrepositories.common.ApiConstants.ORDER
import com.test.searchrepositories.common.ApiConstants.PAGE
import com.test.searchrepositories.common.ApiConstants.PER_PAGE
import com.test.searchrepositories.common.ApiConstants.QUERY
import com.test.searchrepositories.common.ApiConstants.SORT
import com.test.searchrepositories.data.models.server.ListResponseModel
import com.test.searchrepositories.data.models.server.RepositoryModel
import com.test.searchrepositories.data.network.api.ApiRepositories
import com.test.searchrepositories.data.network.api.ApiResponseModel
import com.test.searchrepositories.domain.repository.IRepoRepository
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val api: ApiRepositories
) : BaseRepositoryImpl(), IRepoRepository {

    override suspend fun searchRepositories(
        query: String,
        sort: String,
        order: String,
        page: Int,
        perPage: Int
    )  : ApiResponseModel<ListResponseModel<RepositoryModel>> {
        val queryMap = HashMap<String, String>()
        queryMap[QUERY] = query
        queryMap[SORT] = sort
        queryMap[ORDER] = order
        queryMap[PAGE] = page.toString()
        queryMap[PER_PAGE] = perPage.toString()
        return callApi(api.searchRepositories(queryMap))
    }
}