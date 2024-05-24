package com.kashif.newsapp.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.kashif.newsapp.R

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
data class Page(val title:String,val description:String,@DrawableRes val image :Int)

val pages= listOf(
    Page(title = "News App tamplete",
        description = "A modern news app built with Jetpack Compose and a clean architecture using MVVM. "
                ,
        image = R.drawable.intro_1),
    Page(title = "Support latest technologies",
        description = "The app supports local data storage with DataStore Preferences and Room Database, " +
                " dependency injection with Dagger Hilt, network requests with Retrofit, and efficient data " +
                " loading with Paging 3 library.",
        image = R.drawable.intro_2),
    Page(title = "Linkedin profile",
        description = "https://www.linkedin.com/in/mohammad-kashif-ansari/",
        image = R.drawable.intro_3),

)
