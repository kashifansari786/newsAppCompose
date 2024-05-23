package com.kashif.newsapp.presentation.bookmark

import com.kashif.newsapp.domain.model.Article

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */
data class BookmarkState(val article: List<Article> = emptyList())
