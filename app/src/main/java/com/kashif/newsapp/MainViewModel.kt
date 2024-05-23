package com.kashif.newsapp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashif.newsapp.domain.useCases.app_entry.AppEntryUseCases
import com.kashif.newsapp.presentation.navGraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val appEntryUseCases: AppEntryUseCases):ViewModel() {

    var splashCondition by mutableStateOf(true)
        //This keyword makes the startDestination variable writable inside the class where it's declared but read-only outside of that class.
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        //This keyword makes the startDestination variable writable inside the class where it's declared but read-only outside of that class.
         private set

    init {
        appEntryUseCases.readAppEntry().onEach {shouldStartFromHomeScreen->
            Log.d("inside_mainacti","appentry:- "+shouldStartFromHomeScreen)
            if(shouldStartFromHomeScreen)
                startDestination=Route.NewsNavigation.route
            else
                startDestination=Route.AppStartNavigation.route

            delay(300)
            splashCondition=false
        }.launchIn(viewModelScope)
    }
}