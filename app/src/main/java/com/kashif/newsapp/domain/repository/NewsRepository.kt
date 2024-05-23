package com.kashif.newsapp.domain.repository

import androidx.paging.PagingData
import com.kashif.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
interface NewsRepository {

    fun getNews(sources:List<String>):Flow<PagingData<Article>>

    fun searchNews(searchQuery:String,sources:List<String>):Flow<PagingData<Article>>

    suspend fun updateArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    fun selectArticles():Flow<List<Article>>
    suspend fun selectArticle(url:String):Article?

}