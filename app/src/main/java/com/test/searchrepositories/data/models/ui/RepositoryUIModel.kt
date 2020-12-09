package com.test.searchrepositories.data.models.ui

data class RepositoryUIModel (
    val id: Int,
    val nodeId: String,
    val name: String,
    val private: Boolean,
    val htmlUrl: String,
    val description: String
)