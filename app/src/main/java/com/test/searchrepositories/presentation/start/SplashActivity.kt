package com.test.searchrepositories.presentation.start

import android.os.Bundle
import com.test.searchrepositories.R
import com.test.searchrepositories.databinding.ActivitySplashBinding
import com.test.searchrepositories.presentation.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewBinding.root.postDelayed({ startSearchActivity() }, 200)
    }

    private fun startSearchActivity() {
    }

    override fun inflateViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

}