package com.kashif.newsapp.data.remote.DtO

import com.kashif.newsapp.domain.model.Article

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
