package com.kashif.newsapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.paging.compose.LazyPagingItems
import com.kashif.newsapp.domain.model.Article
import com.kashif.newsapp.presentation.Dimens
import com.kashif.newsapp.R
import com.kashif.newsapp.presentation.common.ArticleList
import com.kashif.newsapp.presentation.common.SearchBar
import com.kashif.newsapp.presentation.navGraph.Route


/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(articles:LazyPagingItems<Article>,
               navigateToSearch:()->Unit,
               navigateToDetails:(Article)->Unit){
    val titles by remember {
        derivedStateOf {
            if(articles.itemCount>10){
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5 "){
                        it.title
                    }
            }else{
                ""
            }
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = Dimens.MediumPadding24)
        .statusBarsPadding()) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription =null, 
            modifier = Modifier
                .width(Dimens.ImageWidth)
                .height(Dimens.ImageHeight)
                .padding(horizontal = Dimens.MediumPadding24))
        Spacer(modifier = Modifier.height(Dimens.MediumPadding24))
        SearchBar(modifier = Modifier.padding(horizontal = Dimens.SmallIconSize),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {navigateToSearch()},
            onSearch = {})
        Spacer(modifier = Modifier.height(Dimens.MediumPadding24))
        Text(text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.MediumPadding24)
                .basicMarquee(),
            fontSize = Dimens.FontSize,
            color = colorResource(id = R.color.placeholder))
        Spacer(modifier = Modifier.height(Dimens.MediumPadding24))
        ArticleList(
            articles = articles,
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding24),
            onClick = {
                navigateToDetails(it)
            })
    }
}