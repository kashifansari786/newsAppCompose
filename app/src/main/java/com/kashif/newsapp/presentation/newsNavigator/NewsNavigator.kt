package com.kashif.newsapp.presentation.newsNavigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.kashif.newsapp.R
import com.kashif.newsapp.domain.model.Article
import com.kashif.newsapp.presentation.bookmark.BookmarkScreen
import com.kashif.newsapp.presentation.bookmark.BookmarkViewModel
import com.kashif.newsapp.presentation.details.DetailScreen
import com.kashif.newsapp.presentation.details.DetailsEvent
import com.kashif.newsapp.presentation.details.DetailsViewModel
import com.kashif.newsapp.presentation.home.HomeScreen
import com.kashif.newsapp.presentation.home.HomeViewModel
import com.kashif.newsapp.presentation.navGraph.Route
import com.kashif.newsapp.presentation.newsNavigator.components.BottomNavigationItems
import com.kashif.newsapp.presentation.newsNavigator.components.NewsBottomNavigation
import com.kashif.newsapp.presentation.search.SearchScreen
import com.kashif.newsapp.presentation.search.SearchViewModel

/**
 * Created by Mohammad Kashif Ansari on 22,May,2024
 */

@Composable
fun NewsNavigator(updateInternetConnectivity: Boolean) {

    val context= LocalContext.current
    val bottomNavigationItems= remember {
        listOf(
            BottomNavigationItems(icon = R.drawable.ic_home, text = "Home"),
                    BottomNavigationItems(icon = R.drawable.ic_search, text = "Search"),
        BottomNavigationItems(icon = R.drawable.ic_bookmark, text = "Bookmark")
        )
    }
    val navController= rememberNavController()
    val backstackState=navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem= remember(key1 = backstackState) {
        when(backstackState?.destination?.route){
            Route.HomeScreen.route->0
            Route.SearchScreen.route->1
            Route.BookmarkScreen.route->2
            else->0
        }
    }



    val isBottomBarVisible= remember(key1 = backstackState) {
        backstackState?.destination?.route==Route.HomeScreen.route ||
                backstackState?.destination?.route==Route.SearchScreen.route ||
                backstackState?.destination?.route==Route.BookmarkScreen.route
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(isBottomBarVisible){
                NewsBottomNavigation(items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = {index->
                        when(index){
                            0-> navigateToTop(
                                navController=navController,
                                route = Route.HomeScreen.route
                            )
                            1-> navigateToTop(
                                navController=navController,
                                route = Route.SearchScreen.route
                            )
                            2-> navigateToTop(
                                navController=navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    })
            }

        }) {
val bottomPadding=it.calculateBottomPadding()
        NavHost(navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)){

                composable(route=Route.HomeScreen.route){

                    val viewModel:HomeViewModel= hiltViewModel()

                        viewModel.state.value.articles?.let {
                            val articles=it.collectAsLazyPagingItems()
                            HomeScreen(articles = articles,
                                navigateToSearch = {
                                    navigateToTop(navController=navController,route=Route.SearchScreen.route)
                                },
                                navigateToDetails = {article->
                                    navigateToDetails(
                                        navController,
                                        article
                                    )
                                },updateInternetConnectivity)
                    }


                }
            composable(route = Route.SearchScreen.route){
                val viewmodel:SearchViewModel= hiltViewModel()
                    val state=viewmodel.state.value
                    SearchScreen(state = state, event = viewmodel::onEvent, naviagateToDetails = {
                        navigateToDetails(navController, article = it)
                    },updateInternetConnectivity)



            }
            composable(route = Route.DetailsScreen.route){
                val viewmodel: DetailsViewModel= hiltViewModel()
                if(viewmodel.sideEffect!=null ){
                    Toast.makeText(context,viewmodel.sideEffect,Toast.LENGTH_SHORT).show()
                    viewmodel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")?.let { article->
                    DetailScreen(article = article, event = viewmodel::onEvent, navigateUp = {navController.navigateUp()},updateInternetConnectivity)
                }
            }
            composable(route = Route.BookmarkScreen.route){
                val viewmodel:BookmarkViewModel= hiltViewModel()
                val state=viewmodel.state.value
                BookmarkScreen(state = state, navigateToDetails = {article->
                    navigateToDetails(navController, article)
                },updateInternetConnectivity)
            }
        }
    }
}

fun updateInternetConnectivity(checkConnectivityStatus: Any) {

}

private fun navigateToTop(navController: NavController,route:String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let{homeScreen->
            popUpTo(homeScreen){
                saveState=true
            }
            restoreState=true
            launchSingleTop=true

        }
    }
}
private fun navigateToDetails(
    navController: NavController,
    article: Article
){
    navController.currentBackStackEntry?.savedStateHandle?.set("article",article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}