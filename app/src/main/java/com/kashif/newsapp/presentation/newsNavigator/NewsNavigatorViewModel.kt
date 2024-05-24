package com.kashif.newsapp.presentation.newsNavigator

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Mohammad Kashif Ansari on 24,May,2024
 */
@HiltViewModel
class NewsNavigatorViewModel @Inject constructor():ViewModel() {
   // val isInternetAvailable= mutableStateOf(false)

    fun updateInternetConnectivity(isConnected: Boolean):Boolean {
        return isConnected

    }
}