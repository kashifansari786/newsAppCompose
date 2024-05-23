package com.kashif.newsapp.data.manager

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.kashif.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import com.kashif.newsapp.util.Constants
import kotlinx.coroutines.flow.map

/**
 * Created by Mohammad Kashif Ansari on 19,May,2024
 */
class LocalUserManagerImpl(private val context:Context):LocalUserManager {
    override suspend fun saveAppEntry() {
        Log.d("inside_mainacti","inside impli save app")
       context.dataStore.edit { settings->
           settings[PreferencesKeys.APP_ENTRY]=true
       }
    }

    override fun readAppEntry(): Flow<Boolean> {
      return context.dataStore.data.map{preferences->
            preferences[PreferencesKeys.APP_ENTRY]?:false
      }
    }
}
//extension value for Context with DataStore with preferences
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)
//store data preference keys
private object PreferencesKeys{
    val APP_ENTRY= booleanPreferencesKey(name = Constants.APP_ENTRY)
}
