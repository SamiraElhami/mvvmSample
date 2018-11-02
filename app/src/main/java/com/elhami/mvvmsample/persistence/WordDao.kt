package com.elhami.mvvmsample.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

//Generate queries
@Dao
interface WordDao {
    /**
     * Select all data from the database.
     */

    @Query("SELECT * FROM words")
    fun getAll(): List<Word>

    /**
     * Insert all data in the database.
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(word: ArrayList<Word>)
}