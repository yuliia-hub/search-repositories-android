package com.test.searchrepositories.presentation.base.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingViewHolder<In>(viewBinding: ViewBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    abstract fun bind(item: In)
}