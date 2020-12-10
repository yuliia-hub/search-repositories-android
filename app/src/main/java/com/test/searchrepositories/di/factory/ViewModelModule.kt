package com.test.searchrepositories.di.factory

import androidx.lifecycle.ViewModelProvider
import com.test.searchrepositories.di.module.FragmentsViewModelModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [FragmentsViewModelModule::class]
)
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}