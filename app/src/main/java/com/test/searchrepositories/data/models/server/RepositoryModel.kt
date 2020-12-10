package com.test.searchrepositories.data.models.server

import com.google.gson.annotations.SerializedName

data class RepositoryModel (
    @SerializedName("id") val id: Long,
    @SerializedName("node_id") val nodeId: String,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("private") val isPrivate: Boolean,
    @SerializedName("html_url") val htmlUrl: String,
    @SerializedName("description") val description: String,
    @SerializedName("fork") val isFork: Boolean,
    @SerializedName("stargazers_count") val stars: Long,
)