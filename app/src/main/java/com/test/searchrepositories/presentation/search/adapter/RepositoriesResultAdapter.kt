package com.test.searchrepositories.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.searchrepositories.data.models.server.RepositoryModel
import com.test.searchrepositories.databinding.LiRepositoryBinding
import com.test.searchrepositories.presentation.base.adapter.BaseViewBindingAdapter

class RepositoriesResultAdapter(
) : BaseViewBindingAdapter<RepositoryModel, RepositoryViewHolder>() {

    override fun createViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): RepositoryViewHolder {
        val viewBinding = LiRepositoryBinding.inflate(inflater, parent, false)
        return RepositoryViewHolder(viewBinding)
    }
}