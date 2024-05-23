package com.kashif.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kashif.newsapp.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUseCases: NewsUseCases):ViewModel() {

    val news=newsUseCases.getNews(source = listOf("bbc-news","abc-news","al-jazeera-english")) //it will give you only this channel news
        .cachedIn(viewModelScope) //it will save configuration changes
}