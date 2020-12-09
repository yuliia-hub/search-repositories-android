package com.test.searchrepositories.data.models.server

import com.google.gson.annotations.SerializedName

data class ListResponseModel<T>(
    @SerializedName("total_count") val total: Long? = null,
    @SerializedName("incomplete_results") val incompleteResults: Boolean = false,
    @SerializedName("items") val items: List<T>? = null
)