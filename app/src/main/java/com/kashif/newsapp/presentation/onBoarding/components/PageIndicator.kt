package com.kashif.newsapp.presentation.onBoarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.kashif.newsapp.presentation.Dimens

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */

@Composable
fun PageIndicator(modifier: Modifier= Modifier,
                  pageSize:Int,
                  selectedPage:Int,
                  selectedColor:Color=MaterialTheme.colorScheme.primary,
                  unselectedColor:Color= Color.Blue
){
    Row (modifier=modifier,
        horizontalArrangement = Arrangement.SpaceBetween){
        //repeat number of pages
        repeat(pageSize){page->
            Box(
                modifier = Modifier
                    .size(Dimens.IndicatorSize)
                    .clip(CircleShape)
                    .background(color = if (page == selectedPage) selectedColor else unselectedColor)
            )
        }
    }
}