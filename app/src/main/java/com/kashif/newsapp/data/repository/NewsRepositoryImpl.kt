package com.kashif.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kashif.newsapp.data.local.NewsDao
import com.kashif.newsapp.data.remote.NewsApi
import com.kashif.newsapp.data.remote.NewsPagingSource
import com.kashif.newsapp.data.remote.SearchNewsPagingSource
import com.kashif.newsapp.domain.model.Article
import com.kashif.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
class NewsRepositoryImpl(private val newsApi: NewsApi,
                         private val newsDao: NewsDao):NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(newsApi = newsApi,
                    source = sources.joinToString( separator="," ))
            }).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(seachQuery =searchQuery,
                    newsApi = newsApi,
                    source = sources.joinToString( separator="," ))
            }).flow
    }

    override suspend fun updateArticle(article: Article) {
        newsDao.update(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
       return newsDao.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
      return newsDao.getArticle(url)
    }
}