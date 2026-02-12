package com.behnamuix.newspaper.database.dataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow

interface DataStore {
    fun saveDarkMode(context: Context, isDark: Boolean)

    fun getDarkMode(context: Context): Flow<Boolean>

    fun saveLastOpenedNews(context: Context, title: String)

    fun getLastOpenedNews(context: Context): Flow<String>
}


