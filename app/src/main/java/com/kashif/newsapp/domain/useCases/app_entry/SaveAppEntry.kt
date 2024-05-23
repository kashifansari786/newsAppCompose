package com.kashif.newsapp.domain.useCases.app_entry

import android.util.Log
import com.kashif.newsapp.domain.manager.LocalUserManager

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
class SaveAppEntry(private val localUserManager:LocalUserManager) {

    //operator function so it will call by using class name
    suspend operator fun invoke(){
        Log.d("inside_mainacti","inside_saveentry invoke")
        localUserManager.saveAppEntry()
    }
}