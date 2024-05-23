package com.kashif.newsapp.presentation.search

import androidx.paging.PagingData
import com.kashif.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */
data class SearchState(val searchQuery:String="",
    val articles:Flow<PagingData<Article>>?=null) {
}