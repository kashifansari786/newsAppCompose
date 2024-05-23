package com.kashif.newsapp.presentation.details

import com.kashif.newsapp.domain.model.Article

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */
sealed class DetailsEvent {

    data class  UpdateDeleteArticle(val article: Article):DetailsEvent()
    object RemoveSideEffect:DetailsEvent()
}