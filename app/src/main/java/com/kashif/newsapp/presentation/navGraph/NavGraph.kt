package com.kashif.newsapp.presentation.navGraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.kashif.newsapp.presentation.common.ConnectionStatus
import com.kashif.newsapp.presentation.common.currentConnectivityStatus
import com.kashif.newsapp.presentation.common.observeConnectivityAsFlow
import com.kashif.newsapp.presentation.newsNavigator.NewsNavigator
import com.kashif.newsapp.presentation.newsNavigator.NewsNavigatorViewModel
import com.kashif.newsapp.presentation.onBoarding.OnBoardingScreen
import com.kashif.newsapp.presentation.onBoarding.OnBoardingViewModel

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */

@Composable
fun NavGraph(startDestination:String){
    val navController= rememberNavController()

    NavHost(navController = navController, startDestination=startDestination){
        navigation(route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route){
            composable(route = Route.OnBoardingScreen.route){
                val viewmodel:OnBoardingViewModel= hiltViewModel()
                OnBoardingScreen {
                    // viewmodel::onEvent
                    viewmodel.onEvent(it)
                }
            }
        }
        navigation(route = Route.NewsNavigation.route, startDestination = Route.NewsNavigatorScreen.route){
            composable(route=Route.NewsNavigatorScreen.route){
                val viewmodel:NewsNavigatorViewModel= hiltViewModel()
                NewsNavigator(viewmodel.updateInternetConnectivity(checkConnectivityStatus()))
            }

        }
    }
}
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun connectivityStatus(): State<ConnectionStatus> {
    val context= LocalContext.current
    return produceState(initialValue = context.currentConnectivityStatus) {
        context.observeConnectivityAsFlow().collect{
            value=it
        }
    }
}
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun checkConnectivityStatus(): Boolean {
    val connection by connectivityStatus()
    val isConnected=connection === ConnectionStatus.Available
    return isConnected
}
