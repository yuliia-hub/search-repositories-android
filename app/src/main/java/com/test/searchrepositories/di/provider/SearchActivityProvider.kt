package com.test.searchrepositories.di.provider

import com.test.searchrepositories.presentation.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchActivityProvider {

    @ContributesAndroidInjector
    abstract fun provideSearchFragment(): SearchFragment
}