package com.kashif.newsapp.domain.useCases.news

import com.kashif.newsapp.data.local.NewsDao
import com.kashif.newsapp.domain.model.Article
import com.kashif.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */
class SelectArticle (private val newsRepository: NewsRepository) {

     suspend operator fun invoke(url:String): Article? {
        return  newsRepository.selectArticle(url)
    }
}