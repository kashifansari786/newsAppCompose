package com.kashif.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kashif.newsapp.presentation.navGraph.NavGraph
import com.kashif.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    @Inject
//    lateinit var appEntryUseCases:AppEntryUseCases
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashCondition
            }
        }
//        lifecycleScope.launch {
//            appEntryUseCases.readAppEntry().collect{
//                Log.d("inside_mainacti","value:- "+it)
//            }
//        }
        setContent {
            NewsAppTheme {
                val isSystemInDarkMode= isSystemInDarkTheme()
                val systemController= rememberSystemUiController()
                SideEffect {
                    systemController.setStatusBarColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }
                // A surface container using the 'background' color from the theme

                    Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)){
                        val startDestination=viewModel.startDestination
                        NavGraph(startDestination = startDestination)

                    }
            }
        }
    }
}