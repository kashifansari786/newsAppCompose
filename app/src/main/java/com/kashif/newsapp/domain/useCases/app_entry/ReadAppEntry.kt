package com.kashif.newsapp.domain.useCases.app_entry

import com.kashif.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
class ReadAppEntry(private val localUserManager: LocalUserManager) {

    //operator function so it will call by using class name
    operator fun invoke():Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}