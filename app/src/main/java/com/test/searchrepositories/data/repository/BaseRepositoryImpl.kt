package com.test.searchrepositories.data.repository

import com.test.searchrepositories.data.network.api.ApiResponseModel
import com.test.searchrepositories.data.network.api.ErrorResult
import com.test.searchrepositories.data.network.api.SuccessResult
import retrofit2.Call
import retrofit2.awaitResponse
import java.io.IOException
import javax.inject.Inject

open class BaseRepositoryImpl @Inject constructor() {

    suspend fun <T> callApi(call: Call<T>): ApiResponseModel<T> =
        try {
            val response = call.awaitResponse()
            when (response.isSuccessful) {
                true -> response.body()?.let { SuccessResult(it) } ?: ErrorResult()
                else -> ErrorResult(response.code(), response.message())
            }
        } catch (ex: IOException) {
            ErrorResult(message = ex.localizedMessage)
        }
}