package com.kashif.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.kashif.newsapp.domain.model.Article
import com.kashif.newsapp.presentation.Dimens
import com.kashif.newsapp.presentation.common.ArticleList
import com.kashif.newsapp.presentation.common.SearchBar

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */
@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvents) -> Unit,
    naviagateToDetails: (Article) -> Unit,
    updateInternetConnectivity: Boolean
){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(
            top = Dimens.MediumPadding24,
            start = Dimens.MediumPadding24,
            end = Dimens.MediumPadding24
        )
        .statusBarsPadding()
        ) {
        SearchBar(text = state.searchQuery, 
            readOnly = false, 
            onValueChange = {event(SearchEvents.UpdateSearchQuery(it))}, 
            onSearch = {event(SearchEvents.SearchNews)}) 
        Spacer(modifier = Modifier.height(Dimens.MediumPadding24))
        state.articles?.let { 
            val articles=it.collectAsLazyPagingItems()
            ArticleList(articles = articles,
                onClick = {naviagateToDetails(it)},
                updateInternetConnectivity = updateInternetConnectivity)
        }

    }
}