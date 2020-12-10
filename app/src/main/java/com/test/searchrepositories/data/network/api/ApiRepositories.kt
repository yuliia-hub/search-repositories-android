package com.test.searchrepositories.data.network.api

import com.test.searchrepositories.common.ApiConstants.SEARCH_REPOSITORIES
import com.test.searchrepositories.data.models.server.ListResponseModel
import com.test.searchrepositories.data.models.server.RepositoryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface ApiRepositories {

    @Headers("Accept:application/vnd.github.v3.text-match+json")
    @GET(SEARCH_REPOSITORIES)
    fun searchRepositories(
        @QueryMap options: Map<String, String>
    ): Call<ListResponseModel<RepositoryModel>>
}