package com.test.searchrepositories.presentation.start

import android.os.Bundle
import com.test.searchrepositories.databinding.ActivitySplashBinding
import com.test.searchrepositories.presentation.base.BaseActivity
import com.test.searchrepositories.presentation.search.SearchActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    companion object {
        const val SPLASH_DURATION = 300L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.root.postDelayed({ startSearchActivity() }, SPLASH_DURATION)
    }

    override fun inflateViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    private fun startSearchActivity() {
        startActivity(SearchActivity.newInstance(this))
        finish()
    }

}