package com.test.searchrepositories.presentation.search.adapter

import com.test.searchrepositories.data.models.server.RepositoryModel
import com.test.searchrepositories.databinding.LiRepositoryBinding
import com.test.searchrepositories.presentation.base.adapter.BaseViewBindingViewHolder

class RepositoryViewHolder(
    private val binding: LiRepositoryBinding
): BaseViewBindingViewHolder<RepositoryModel>(binding) {

    override fun bind(item: RepositoryModel) {
        with(binding) {
            liRepositoryTitle.text = item.name
            liRepositoryDescription.text = item.description
            liRepositoryUrl.text = item.htmlUrl
            liRepositoryStars.text = item.stars.toString()
        }
    }
}