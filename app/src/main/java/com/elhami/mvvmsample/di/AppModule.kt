package com.elhami.mvvmsample.di

import android.app.Application
import android.arch.persistence.room.Room
import com.elhami.mvvmsample.persistence.WordDao
import com.elhami.mvvmsample.persistence.WordDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): WordDatabase {
        return Room.databaseBuilder(app.applicationContext,
            WordDatabase::class.java, "word.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideWordDao(db: WordDatabase): WordDao {
        return db.wordDao()
    }
}