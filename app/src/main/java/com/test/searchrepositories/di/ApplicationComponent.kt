package com.test.searchrepositories.di

import android.app.Application
import android.content.Context
import com.test.searchrepositories.di.module.ActivityModule
import com.test.searchrepositories.di.module.ApplicationModule
import com.test.searchrepositories.di.module.NetworkModule
import com.test.searchrepositories.di.module.RepositoryModule
import com.test.searchrepositories.presentation.SearchRepositoriesApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class, NetworkModule::class,
        ActivityModule::class, ApplicationModule::class, RepositoryModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<SearchRepositoriesApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun context(): Context
    fun okHttpClient () : OkHttpClient
}