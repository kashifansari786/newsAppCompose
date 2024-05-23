package com.kashif.newsapp.domain.useCases.news

import androidx.paging.PagingData
import com.kashif.newsapp.data.remote.NewsPagingSource
import com.kashif.newsapp.domain.model.Article
import com.kashif.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
class GetNews(private val newsRepository: NewsRepository) {

    operator fun invoke(source: List<String>) : Flow<PagingData<Article>> {
        return  newsRepository.getNews(sources = source)
    }
}