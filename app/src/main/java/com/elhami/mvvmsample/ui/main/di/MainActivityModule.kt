package com.elhami.mvvmsample.ui.main.di

import com.elhami.mvvmsample.persistence.WordDao
import com.elhami.mvvmsample.ui.main.MainActivityVM
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    fun provideViewModel(wordDao: WordDao) = MainActivityVM(wordDao)
}