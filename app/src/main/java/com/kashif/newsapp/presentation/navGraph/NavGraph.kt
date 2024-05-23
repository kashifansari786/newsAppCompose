package com.kashif.newsapp.presentation.navGraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.kashif.newsapp.presentation.home.HomeScreen
import com.kashif.newsapp.presentation.home.HomeViewModel
import com.kashif.newsapp.presentation.newsNavigator.NewsNavigator
import com.kashif.newsapp.presentation.onBoarding.OnBoardingScreen
import com.kashif.newsapp.presentation.onBoarding.OnBoardingViewModel
import com.kashif.newsapp.presentation.search.SearchScreen
import com.kashif.newsapp.presentation.search.SearchViewModel

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
                NewsNavigator()
            }

        }
    }
}