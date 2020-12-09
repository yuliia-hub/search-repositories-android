package com.test.searchrepositories.presentation

import androidx.viewbinding.BuildConfig
import com.test.searchrepositories.di.ApplicationComponent
import com.test.searchrepositories.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class SearchRepositoriesApp : DaggerApplication() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
            .also { appComponent = it }
}