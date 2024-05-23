package com.kashif.newsapp.presentation.onBoarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashif.newsapp.domain.useCases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val appEntryUseCases: AppEntryUseCases):ViewModel(){

    fun onEvent(event:OnBoardingEvent){
        Log.d("inside_mainacti","event:- "+event)
        when(event){
            is OnBoardingEvent.SaveAppEntry->{
                saveAppEntry()
            }
        }
    }
    private fun saveAppEntry(){
        viewModelScope.launch {
            Log.d("inside_mainacti","saveappenrty:- ")
            appEntryUseCases.saveAppEntry()
        }
    }
}