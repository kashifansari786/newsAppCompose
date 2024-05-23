package com.kashif.newsapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kashif.newsapp.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */
@HiltViewModel
class SearchViewModel @Inject constructor(private val newsUseCases:NewsUseCases):ViewModel() {


    private val _state= mutableStateOf(SearchState())
    val state : State<SearchState> = _state


    fun onEvent(event:SearchEvents){
        when(event){
            is SearchEvents.UpdateSearchQuery->{
                _state.value=_state.value.copy(searchQuery = event.searchQuery)
            }
            is SearchEvents.SearchNews->{
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles=newsUseCases.searchNews(searchQuery = _state.value.searchQuery,
            source  = listOf("bbc-news","abc-news","al-jazeera-english")).cachedIn(viewModelScope)
        _state.value = _state.value.copy(articles=articles)
    }
}