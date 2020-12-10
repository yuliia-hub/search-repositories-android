package com.test.searchrepositories.domain.useCase

import com.test.searchrepositories.data.network.api.ApiResponseModel

abstract class BaseUseCase<T> {

    abstract suspend fun execute(): ApiResponseModel<T>
}