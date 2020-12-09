package com.test.searchrepositories.presentation.search.adapter

import com.test.searchrepositories.data.models.ui.RepositoryUIModel
import com.test.searchrepositories.databinding.LiRepositoryBinding
import com.test.searchrepositories.presentation.base.adapter.BaseViewBindingViewHolder

class RepositoryViewHolder(
    private val binding: LiRepositoryBinding
): BaseViewBindingViewHolder<RepositoryUIModel>(binding) {

    override fun bind(item: RepositoryUIModel) {
        with(binding) {
            liRepositoryTitle.text = item.name
            liRepositoryDescription.text = item.description
            liRepositoryUrl.text = item.htmlUrl
        }
    }
}