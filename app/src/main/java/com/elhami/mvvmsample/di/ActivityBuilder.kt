package com.elhami.mvvmsample.di

import com.elhami.mvvmsample.ui.main.MainActivity
import com.elhami.mvvmsample.ui.main.di.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity
}