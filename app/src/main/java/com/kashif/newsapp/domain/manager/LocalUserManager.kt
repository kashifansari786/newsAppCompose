package com.kashif.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow


/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
//interface is created for storing app on boarding screen state
interface LocalUserManager {

    suspend fun saveAppEntry()
    fun readAppEntry(): Flow<Boolean>
}