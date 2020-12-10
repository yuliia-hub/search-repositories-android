package com.test.searchrepositories.di.module

import com.test.searchrepositories.di.provider.SearchActivityProvider
import com.test.searchrepositories.presentation.search.SearchActivity
import com.test.searchrepositories.presentation.start.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [SearchActivityProvider::class])
    abstract fun bindSearchActivity(): SearchActivity
}