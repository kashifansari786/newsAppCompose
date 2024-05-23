package com.kashif.newsapp.presentation.search

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */
sealed class SearchEvents {

    data class UpdateSearchQuery(val searchQuery:String):SearchEvents()

    object SearchNews:SearchEvents()
}