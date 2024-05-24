package com.kashif.newsapp.presentation.home

import androidx.paging.PagingData
import com.kashif.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Kashif Ansari on 24,May,2024
 */
data class HomeState(val articles: Flow<PagingData<Article>>?=null)