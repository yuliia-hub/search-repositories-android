package com.test.searchrepositories.presentation.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _viewBinding: VB? = null
    protected val viewBinding: VB get() = _viewBinding!!

    abstract fun inflateViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = inflateViewBinding()
        setContentView(viewBinding.root)
    }

    protected fun changeFragment(fragment: BaseFragment<*>, containerId: Int, withBackStack: Boolean = false) {
        supportFragmentManager.beginTransaction()
             .replace(containerId, fragment)
             .apply { if (withBackStack) addToBackStack(fragment.javaClass.simpleName) }
             .commit()
    }
}