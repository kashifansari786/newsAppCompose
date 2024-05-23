package com.kashif.newsapp.presentation.onBoarding

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kashif.newsapp.presentation.Dimens
import com.kashif.newsapp.presentation.common.NewsButton
import com.kashif.newsapp.presentation.common.NewsTextButton
import com.kashif.newsapp.presentation.onBoarding.components.OnBoarding
import com.kashif.newsapp.presentation.onBoarding.components.PageIndicator
import kotlinx.coroutines.launch

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(event:(OnBoardingEvent)->Unit){
    Column(modifier = Modifier.fillMaxSize()) {
        //horizontal pager state using rememberstate
        val pagerState= rememberPagerState(initialPage = 0) {
            //number of pages
            pages.size
        }
        val buttonState=remember{
            derivedStateOf {
                when(pagerState.currentPage){
                    0-> listOf("","Next")
                    1-> listOf("Back","Next")
                    2-> listOf("Back","Get Started")
                    else-> listOf("","")
                }
            }
        }
        //horizontal pager which slide to next page
        HorizontalPager(state = pagerState) {index->
            OnBoarding(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.MediumPadding24)
            .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            PageIndicator(
                pageSize = pages.size,
                selectedPage = pagerState.currentPage,
                modifier = Modifier.width(Dimens.PageIndicatorWidth)
            )


            Row(verticalAlignment = Alignment.CenterVertically) {
                //coroutine scope
                val scope = rememberCoroutineScope()
                //check buttonState first position is empty or not
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(text = buttonState.value[0],
                        onCLick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        })
                }
                NewsButton(text = buttonState.value[1], onCLick = {
                    scope.launch {
                        Log.d("inside_mainacti","pagerstate:- "+pagerState.currentPage)
                        if (pagerState.currentPage == 2) {
                            //navigate to home screen
                            event(OnBoardingEvent.SaveAppEntry)
                        } else {
                            pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                        }
                    }
                })
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}