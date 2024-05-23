package com.kashif.newsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.kashif.newsapp.presentation.Dimens.MediumPadding24
import com.kashif.newsapp.R
import com.kashif.newsapp.domain.model.Article
import com.kashif.newsapp.presentation.common.ArticleList
import com.kashif.newsapp.presentation.navGraph.Route

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */

@Composable
fun BookmarkScreen(state:BookmarkState,
                   navigateToDetails:(Article)->Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .padding(
            top = MediumPadding24,
            start = MediumPadding24, end = MediumPadding24
        )) {
        Text(text = "Bookmark",
            style = MaterialTheme.typography.displayMedium .copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title))
        Spacer(modifier = Modifier.height(MediumPadding24))
        ArticleList(articles = state.article, onClick = {navigateToDetails(it)})
    }
}