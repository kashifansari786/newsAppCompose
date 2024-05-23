package com.kashif.newsapp.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kashif.newsapp.domain.model.Article
import com.kashif.newsapp.presentation.Dimens.ArticleImageHeight
import com.kashif.newsapp.presentation.Dimens.MediumPadding24
import com.kashif.newsapp.presentation.details.components.DetailsTopBar
import com.kashif.newsapp.R

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */

@Composable
fun DetailScreen(article:Article,
                 event:(DetailsEvent)->Unit,
                 navigateUp:()->Unit){

    val context= LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()) {
            DetailsTopBar(
                onBrowsingClick = { Intent(Intent.ACTION_VIEW).also {
                    it.data= Uri.parse(article.url)
                    if(it.resolveActivity(context.packageManager) !=null) //adding the query tag inside manifest file to find suitable activity
                        context.startActivity(it)

                } },
                onShareClick = {
                               Intent(Intent.ACTION_SEND).also {
                                   it.putExtra(Intent.EXTRA_TEXT,article.url)
                                   it.type="text/plain"
                                   if(it.resolveActivity(context.packageManager)!=null)
                                       context.startActivity(it)
                               }
                },
                onBookmarkClick = { event(DetailsEvent.UpdateDeleteArticle(article)) },
                onBackClick = navigateUp)
        LazyColumn (modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding24,
                end = MediumPadding24,
                top= MediumPadding24
            )
        ){
                item{
                    AsyncImage(model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
                        contentDescription =null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(ArticleImageHeight)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop)
                    Spacer(modifier = Modifier.height(MediumPadding24))
                    Text(text = article.title,
                        style = MaterialTheme.typography.displaySmall,
                        color = colorResource(id = R.color.text_title))
                    Text(text = article.content,
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(id = R.color.body))
                }
        }
    }

}

