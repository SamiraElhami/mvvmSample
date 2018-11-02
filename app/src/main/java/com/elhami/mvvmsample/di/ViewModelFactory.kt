package com.elhami.mvvmsample.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.elhami.mvvmsample.persistence.WordDatabase
import com.elhami.mvvmsample.ui.main.MainActivityVM

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityVM::class.java)) {
            val db =  Room.databaseBuilder(activity.applicationContext,
                WordDatabase::class.java, "word.db")
                .build()
            return MainActivityVM(db.wordDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}