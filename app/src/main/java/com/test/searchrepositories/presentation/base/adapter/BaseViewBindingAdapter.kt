package com.test.searchrepositories.presentation.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewBindingAdapter<In, Vh : BaseViewBindingViewHolder<In>>() : RecyclerView.Adapter<Vh>() {

    private var list: MutableList<In>? = mutableListOf()

    override fun onBindViewHolder(holder: Vh, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh =
        createViewHolder(LayoutInflater.from(parent.context), parent, viewType)

    abstract fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): Vh

    override fun getItemCount(): Int = list?.size ?: 0

    fun getItems(): List<In>? = list

    fun setItems(list: List<In>?) {
        this.list = list?.toMutableList()
        notifyDataSetChanged()
    }

    fun deleteAll() {
        list?.clear()
        notifyDataSetChanged()
    }

    private fun getItem(position: Int): In? = list?.getOrNull(position)
}