package com.test.searchrepositories.data.network.api

sealed class ApiResponseModel<T>

data class SuccessResult<T>(val body: T) : ApiResponseModel<T>()

data class ErrorResult<T>(
    val code: Int = 0,
    val message: String? = "unknown error"
) : ApiResponseModel<T>()
