package com.kashif.newsapp.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashif.newsapp.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */
@HiltViewModel
class BookmarkViewModel @Inject constructor(private val newsUseCases: NewsUseCases):ViewModel() {

    private val _state= mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    init {
        getArticles()
    }
    private fun getArticles(){
        newsUseCases.selectArticles().onEach {
            _state.value=_state.value.copy(article = it.asReversed())
        }.launchIn(viewModelScope)
    }
}