package com.test.searchrepositories.presentation.base

import com.test.searchrepositories.data.network.api.ApiResponseModel

abstract class BaseUseCase<T> {

    abstract suspend fun execute(): ApiResponseModel<T>
}