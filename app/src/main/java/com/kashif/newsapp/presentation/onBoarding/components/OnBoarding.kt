package com.kashif.newsapp.presentation.onBoarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.kashif.newsapp.R
import com.kashif.newsapp.presentation.Dimens
import com.kashif.newsapp.presentation.onBoarding.Page

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */

@Composable
fun OnBoarding(
    modifier: Modifier = Modifier,
    page:Page
){
    Column(modifier=modifier) {
        Image(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.6f), painter = painterResource(
            id = page.image
        ), contentDescription = null,
            contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.height(Dimens.MediumPadding24))
        Text(text = page.title,modifier=Modifier.padding(horizontal = Dimens.MediumPadding30),
            style = MaterialTheme.typography.titleLarge .copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small
        ))
        Text(text = page.description,modifier=Modifier.padding(horizontal = Dimens.MediumPadding30),
            style = MaterialTheme.typography.titleMedium,
            color = colorResource(id = R.color.text_medium_gray
            ))
    }
}