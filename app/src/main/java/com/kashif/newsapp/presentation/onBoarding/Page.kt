package com.kashif.newsapp.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.kashif.newsapp.R

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
data class Page(val title:String,val description:String,@DrawableRes val image :Int)

val pages= listOf(
    Page(title = "page1",
        description = "page1Desc",
        image = R.drawable.ic_launcher_background),
    Page(title = "page2",
        description = "page2Desc",
        image = R.drawable.ic_launcher_background),
    Page(title = "page3",
        description = "page3Desc",
        image = R.drawable.ic_launcher_background),
)
