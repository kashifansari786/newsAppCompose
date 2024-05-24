package com.kashif.newsapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kashif.newsapp.domain.useCases.news.NewsUseCases
import com.kashif.newsapp.presentation.search.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUseCases: NewsUseCases):ViewModel() {

    private val _state= mutableStateOf(HomeState())
    val state : State<HomeState> = _state

    init {
        news()
    }
    private fun news() {
        val articles=newsUseCases.getNews(source  = listOf("bbc-news","abc-news","al-jazeera-english")).cachedIn(viewModelScope)
        _state.value = _state.value.copy(articles=articles)
    }

//    val news=newsUseCases.getNews(source = listOf("bbc-news","abc-news","al-jazeera-english")) //it will give you only this channel news
//        .cachedIn(viewModelScope) //it will save configuration changes
}