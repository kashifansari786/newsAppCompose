package com.kashif.newsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kashif.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */
@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun update(article: Article)

    @Delete
     fun delete(article: Article)

    @Query("SELECT * FROM Article")
    fun getArticles():Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE url=:url")
    fun getArticle(url:String):Article?


}