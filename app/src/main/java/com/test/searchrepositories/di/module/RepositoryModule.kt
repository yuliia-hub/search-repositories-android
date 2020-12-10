package com.test.searchrepositories.di.module

import com.test.searchrepositories.data.repository.RepoRepositoryImpl
import com.test.searchrepositories.domain.repository.IRepoRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepoRepository(repository: RepoRepositoryImpl): IRepoRepository
}