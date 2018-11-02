package com.elhami.mvvmsample.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

//Create Primary Table
@Entity(tableName = "words")
data class Word( @PrimaryKey
                @ColumnInfo(name = "txteng")
                 val text_eng: String,
                 @ColumnInfo(name = "txtspa")
                 val text_spa: String)