package com.test.searchrepositories.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.searchrepositories.databinding.FragmentSearchRepositoriesBinding
import com.test.searchrepositories.presentation.base.BaseFragment

class SearchFragment: BaseFragment<FragmentSearchRepositoriesBinding>() {

    override fun bindViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchRepositoriesBinding.inflate(inflater)

}