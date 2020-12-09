package com.test.searchrepositories.presentation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.test.searchrepositories.R
import com.test.searchrepositories.databinding.ActivitySearchBinding
import com.test.searchrepositories.presentation.base.BaseActivity

class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    companion object {
        fun newInstance(context: Context) : Intent =
            Intent(context, SearchActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP }
    }

    override var containerId: Int? = R.id.aSearchContainer

    override fun inflateViewBinding(): ActivitySearchBinding =
        ActivitySearchBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeFragment(SearchFragment.getInstance())
    }
}