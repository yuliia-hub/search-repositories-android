package com.test.searchrepositories.di.module

import androidx.lifecycle.ViewModel
import com.test.searchrepositories.di.key.ViewModelKey
import com.test.searchrepositories.presentation.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel

}