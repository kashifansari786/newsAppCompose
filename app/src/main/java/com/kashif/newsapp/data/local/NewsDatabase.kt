package com.kashif.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.kashif.newsapp.domain.model.Article

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */
@Database(entities = [Article::class], version = 2)
@TypeConverters(NewsTypeConvertor::class)
abstract class NewsDatabase :RoomDatabase(){
    abstract val newsDao:NewsDao
}