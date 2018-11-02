package com.elhami.mvvmsample.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(Word::class)], version = 1)
abstract class WordDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao
}