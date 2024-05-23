package com.kashif.newsapp.domain.useCases.news

import androidx.paging.PagingData
import com.kashif.newsapp.domain.model.Article
import com.kashif.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */
class SearchNews (private val newsRepository: NewsRepository){

    operator fun invoke(searchQuery:String,source:List<String>) : Flow<PagingData<Article>>{
        return newsRepository.searchNews(searchQuery = searchQuery, sources = source)
    }
}