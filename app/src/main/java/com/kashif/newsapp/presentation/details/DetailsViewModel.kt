package com.kashif.newsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashif.newsapp.domain.model.Article
import com.kashif.newsapp.domain.useCases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */
@HiltViewModel
class DetailsViewModel @Inject constructor(private val newsUseCases: NewsUseCases):ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set


    fun onEvent(event:DetailsEvent){
        when(event){
            is DetailsEvent.UpdateDeleteArticle->{
                viewModelScope.launch {
                    handleUpdateDeleteArticle(event.article)
                }
            }
            is DetailsEvent.RemoveSideEffect->{
                sideEffect=null
            }
        }
    }

    private suspend fun handleUpdateDeleteArticle(article: Article) {
        withContext(Dispatchers.IO) {
            val existingArticle = newsUseCases.selectArticle(article.url)
            if (existingArticle == null) {
                updateArticle(article)
            } else {
                deleteArticle(article)
            }
        }

    }

    private suspend fun deleteArticle(article: Article) {
        withContext(Dispatchers.IO) {
            newsUseCases.deleteArticle(article = article)
            sideEffect = "Article Deleted"
        }
    }

    private suspend fun updateArticle(article: Article) {
        withContext(Dispatchers.IO) {
            newsUseCases.updateArticle(article = article)
            sideEffect = "Article Saved"
        }
    }
}